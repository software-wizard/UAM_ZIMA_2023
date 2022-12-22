package pl.psi.creatures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.any;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import pl.psi.EffectAbstract;
import pl.psi.TurnQueue;

import com.google.common.collect.Range;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class CreatureTest {

    private static final int NOT_IMPORTANT = 100;
    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    @Test
    void creatureShouldAttackProperly() {
        // given
        final Creature angel = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(Range.closed(10, 10))
                        .attack(50)
                        .armor(NOT_IMPORTANT)
                        .build())
                .build();
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .attack(NOT_IMPORTANT)
                        .armor(10)
                        .build())
                .build();
        // when
        angel.attack(dragon);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(70);
    }

    @Test
    void creatureShouldNotHealCreatureEvenHasLowerAttackThanDefenderArmor() {
        final Creature angel = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(NOT_IMPORTANT_DMG)
                        .attack(1)
                        .armor(NOT_IMPORTANT)
                        .build())
                .build();
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .attack(NOT_IMPORTANT)
                        .armor(10)
                        .build())
                .build();
        // when
        angel.attack(dragon);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(100);
    }

    @Test
    void defenderShouldCounterAttack() {
        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .attack(NOT_IMPORTANT)
                        .armor(10)
                        .build())
                .build();
        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(Range.closed(10, 10))
                        .attack(10)
                        .build())
                .build();
        // when
        attacker.attack(defender);
        // then
        assertThat(attacker.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void defenderShouldNotCounterAttackWhenIsDie() {
        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .attack(1000)
                        .armor(10)
                        .build())
                .build();
        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(NOT_IMPORTANT_DMG)
                        .attack(20)
                        .armor(5)
                        .build())
                .build();
        // when
        attacker.attack(defender);
        // then
        assertThat(attacker.getCurrentHp()).isEqualTo(100);
    }

    @Test
    void defenderShouldCounterAttackOnlyOncePerTurn() {
        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .attack(NOT_IMPORTANT)
                        .armor(10)
                        .build())
                .build();

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(Range.closed(10, 10))
                        .attack(10)
                        .build())
                .build();

        // when
        attacker.attack(defender);
        attacker.attack(defender);
        // then
        assertThat(attacker.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void attackerShouldNotCounterAttack() {
        final Random randomMock = mock(Random.class);
        when(randomMock.nextInt(anyInt())).thenReturn(3);

        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(5, 5))
                        .attack(NOT_IMPORTANT)
                        .armor(NOT_IMPORTANT)
                        .build())
                .build();

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(1, 10))
                        .attack(NOT_IMPORTANT)
                        .armor(NOT_IMPORTANT)
                        .build())
                .calculator(new DefaultDamageCalculator(randomMock))
                .build();

        // when
        attacker.attack(defender);
        // then
        assertThat(defender.getCurrentHp()).isEqualTo(95);
        assertThat(attacker.getCurrentHp()).isEqualTo(96);
    }

    @Test
    void counterAttackCounterShouldResetAfterEndOfTurn() {
        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build();

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .build())
                .build();

        final TurnQueue turnQueue = new TurnQueue(List.of(attacker), List.of(defender));

        attacker.attack(defender);
        attacker.attack(defender);
        assertThat(attacker.getCurrentHp()).isEqualTo(90);
        turnQueue.next();
        turnQueue.next();
        attacker.attack(defender);
        assertThat(attacker.getCurrentHp()).isEqualTo(80);
        // end of turn
    }

    @Test
    void creatureShouldHealAfterEndOfTurn() {
        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .build())
                .build();

        final Creature selfHealAfterEndOfTurnCreature = new SelfHealAfterTurnCreature(new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build());

        final TurnQueue turnQueue =
                new TurnQueue(List.of(attacker), List.of(selfHealAfterEndOfTurnCreature));

        attacker.attack(selfHealAfterEndOfTurnCreature);
        assertThat(selfHealAfterEndOfTurnCreature.getCurrentHp()).isEqualTo(90);
        turnQueue.next();
        turnQueue.next();
        assertThat(selfHealAfterEndOfTurnCreature.getCurrentHp()).isEqualTo(100);
    }

    @Test
    void rangeCreatureShouldPerformRangedAttackWhenShotsAvailable() {
        final Creature attacker = new RangedAttackCreatureDecorator(new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(Range.closed(10, 10))
                        .build())
                .build(),
                1);
        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build();
        attacker.attack(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void rangeCreatureShouldAttackWithPenaltyWhenInMelee(){
        final Creature attacker = new RangedAttackCreatureDecorator(new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(Range.closed(10, 10))
                        .build())
                .build(),
                0);
        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build();
        attacker.approachedInMelee();
        attacker.attack(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(95);
    }

    @Test
    void rangeCreatureShouldNotBeAbleToShootWhenNoArrowsAvailableAndAttackInMeleeInstead(){
        final Creature attacker = new RangedAttackCreatureDecorator(new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(Range.closed(10, 10))
                        .build())
                .build(),
                0);
        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build();
        attacker.attack(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(95);
    }

    @Test
    void rangeCreatureShouldLoseArrowAfterRangedAttack(){
        final Creature attacker = new RangedAttackCreatureDecorator(new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(Range.closed(10, 10))
                        .build())
                .build(),
                2);
        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build();
        attacker.attack(defender);
        attacker.attack(defender);
        attacker.attack(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(75);
    }
    @Test
    void statisticsIncreaseEffectShouldApplyOnCreatureAndChangeItsStatistics() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(NOT_IMPORTANT_DMG)
                        .armor(10)
                        .specialAttributes(List.of(CreatureSpecialAttributeTypes.EVIL))
                        .build())
                .build();
        final EffectAbstract effect = new PermanentStatsBuffEffect("armor_boost", "armor",
                CreatureStats.builder().armor(5).build(), false);

        creature.inflictEffect(effect);
        assertThat(creature.getArmor()).isEqualTo(15);
    }

    @Test
    public void effectShouldUnapplyAfterTimeExpireAndModifyCreatureStatistics() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(NOT_IMPORTANT_DMG)
                        .armor(10)
                        .specialAttributes(List.of(CreatureSpecialAttributeTypes.EVIL))
                        .build())
                .build();
        EffectAbstract effect = mock(EffectAbstract.class);
        when(effect.getDuration()).thenReturn(1);
        when(effect.getName()).thenReturn("name");
        doReturn(new EffectAbstract.Result(creature.getStats(), creature.getEffectLog().getActiveEffects()))
                .when(effect).applyEffect(
                        any(CreatureStatisticIf.class), eq(creature.getEffectLog().getActiveEffects()), any(CreatureStatisticIf.class));
        doReturn(new EffectAbstract.Result(CreatureStats.builder().armor(5).build(), creature.getEffectLog().getActiveEffects()))
                .when(effect).unapplyEffect(
                        any(CreatureStatisticIf.class), eq(creature.getEffectLog().getActiveEffects()));
        creature.inflictEffect(effect);
        creature.updateNextRound();
        assertThat(creature.getArmor()).isEqualTo(5);
        assertThat(creature.getEffectLog().getEffectsTimeTable().contains(effect)).isEqualTo(false);
        assertThat(creature.getEffectLog().getActiveEffects().contains(effect)).isEqualTo(false);
    }

    @Test
    public void onlyEffectsWithNoTimeLeftShouldUnapply() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(NOT_IMPORTANT_DMG)
                        .armor(10)
                        .specialAttributes(List.of(CreatureSpecialAttributeTypes.EVIL))
                        .build())
                .build();

        EffectAbstract effect = mock(EffectAbstract.class);
        when(effect.getDuration()).thenReturn(3);
        when(effect.getName()).thenReturn("name");
        doReturn(new EffectAbstract.Result(creature.getStats(), creature.getEffectLog().getActiveEffects()))
                .when(effect).applyEffect(
                        any(CreatureStatisticIf.class), eq(creature.getEffectLog().getActiveEffects()), any(CreatureStatisticIf.class));
        doReturn(new EffectAbstract.Result(CreatureStats.builder().armor(5).build(), creature.getEffectLog().getActiveEffects()))
                .when(effect).unapplyEffect(
                        any(CreatureStatisticIf.class), eq(creature.getEffectLog().getActiveEffects()));


        creature.inflictEffect(effect);
        creature.updateNextRound();

        assertThat(creature.getEffectLog().getEffectsTimeTable().size()).isEqualTo(1);
        assertThat(creature.getEffectLog().getActiveEffects().size()).isEqualTo(1);
    }
}


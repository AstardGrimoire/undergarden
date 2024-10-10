package quek.undergarden.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import quek.undergarden.registry.UGCriteria;

import java.util.Optional;

public class UthericInfectionTrigger extends SimpleCriterionTrigger<UthericInfectionTrigger.TriggerInstance> {
	@Override
	public Codec<TriggerInstance> codec() {
		return TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player, double infectionLevel) {
		this.trigger(player, triggerInstance -> triggerInstance.matches(infectionLevel));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<MinMaxBounds.Doubles> infectionLevel) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
				MinMaxBounds.Doubles.CODEC.optionalFieldOf("infectionLevel").forGetter(TriggerInstance::infectionLevel))
			.apply(instance, TriggerInstance::new)
		);

		public static Criterion<?> isInfected() {
			return UGCriteria.UTHERIC_INFECTION.get().createCriterion(new UthericInfectionTrigger.TriggerInstance(Optional.empty(), Optional.of(MinMaxBounds.Doubles.atLeast(1))));
		}

		public static Criterion<?> hasInfectionLevel(int infectionLevel) {
			return UGCriteria.UTHERIC_INFECTION.get().createCriterion(new UthericInfectionTrigger.TriggerInstance(Optional.empty(), Optional.of(MinMaxBounds.Doubles.exactly(infectionLevel))));
		}

		public static Criterion<?> hasInfectionLevel(MinMaxBounds.Doubles infectionLevel) {
			return UGCriteria.UTHERIC_INFECTION.get().createCriterion(new UthericInfectionTrigger.TriggerInstance(Optional.empty(), Optional.of(infectionLevel)));
		}

		public boolean matches(double infectionLevel) {
			return this.infectionLevel.isEmpty() || this.infectionLevel.get().matches(infectionLevel);
		}
	}
}
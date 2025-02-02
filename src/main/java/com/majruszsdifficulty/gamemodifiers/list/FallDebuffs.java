package com.majruszsdifficulty.gamemodifiers.list;

import com.majruszsdifficulty.GameStage;
import com.majruszsdifficulty.gamemodifiers.CustomConditions;
import com.majruszsdifficulty.gamemodifiers.GameModifier;
import com.majruszsdifficulty.gamemodifiers.configs.ProgressiveEffectConfig;
import com.mlib.gamemodifiers.Condition;
import com.mlib.gamemodifiers.contexts.OnDamagedContext;
import com.mlib.gamemodifiers.data.OnDamagedData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;

public class FallDebuffs extends GameModifier {
	final ProgressiveEffectConfig nausea = new ProgressiveEffectConfig( "Nausea", ()->MobEffects.CONFUSION, 0, 8.0 );
	final ProgressiveEffectConfig slowness = new ProgressiveEffectConfig( "Slowness", ()->MobEffects.MOVEMENT_SLOWDOWN, 0, 6.0 );

	public FallDebuffs() {
		super( GameModifier.DEFAULT, "FallDebuffs", "Inflicts several debuffs when taking fall damage." );

		OnDamagedContext onDamaged = new OnDamagedContext( this::applyDebuffs );
		onDamaged.addCondition( new CustomConditions.GameStage( GameStage.Stage.NORMAL ) )
			.addCondition( new Condition.Chance( 1.0 ) )
			.addCondition( new Condition.Excludable() )
			.addCondition( data->data.source.equals( DamageSource.FALL ) && data.event.getAmount() > 2.0f )
			.addConfigs( this.nausea, this.slowness );

		this.addContext( onDamaged );
	}

	private void applyDebuffs( OnDamagedData data ) {
		this.nausea.apply( data.target );
		this.slowness.apply( data.target );
	}
}

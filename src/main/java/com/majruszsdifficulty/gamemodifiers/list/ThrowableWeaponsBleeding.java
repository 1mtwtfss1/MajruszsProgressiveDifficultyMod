package com.majruszsdifficulty.gamemodifiers.list;

import com.majruszsdifficulty.GameStage;
import com.majruszsdifficulty.gamemodifiers.CustomConditions;
import com.majruszsdifficulty.gamemodifiers.GameModifier;
import com.majruszsdifficulty.gamemodifiers.configs.BleedingConfig;
import com.mlib.gamemodifiers.Condition;
import com.mlib.gamemodifiers.contexts.OnDamagedContext;
import com.mlib.gamemodifiers.data.OnDamagedData;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ThrownTrident;

public class ThrowableWeaponsBleeding extends GameModifier {
	final BleedingConfig bleeding = new BleedingConfig();

	public ThrowableWeaponsBleeding() {
		super( GameModifier.DEFAULT, "ThrowableWeaponsBleeding", "All throwable sharp items (arrows, trident etc.) may inflict bleeding." );

		OnDamagedContext onDamaged = new OnDamagedContext( this::applyBleeding );
		onDamaged.addCondition( new CustomConditions.GameStage( GameStage.Stage.NORMAL ) )
			.addCondition( new Condition.Chance( 0.4 ) )
			.addCondition( new Condition.Excludable() )
			.addCondition( new Condition.IsLivingBeing() )
			.addCondition( new Condition.ArmorDependentChance() )
			.addCondition( data->data.source.getDirectEntity() instanceof Arrow || data.source.getDirectEntity() instanceof ThrownTrident )
			.addConfig( this.bleeding );

		this.addContext( onDamaged );
	}

	private void applyBleeding( OnDamagedData data ) {
		this.bleeding.apply( data );
	}
}

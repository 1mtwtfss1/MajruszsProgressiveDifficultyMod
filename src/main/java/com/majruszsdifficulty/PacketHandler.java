package com.majruszsdifficulty;

import com.majruszsdifficulty.entities.TankEntity;
import com.majruszsdifficulty.treasurebags.LootProgressManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

/** Handling connection between server and client. */
public class PacketHandler {
	private static final String PROTOCOL_VERSION = "1";
	public static SimpleChannel CHANNEL;

	public static void registerPacket( final FMLCommonSetupEvent event ) {
		CHANNEL = NetworkRegistry.newSimpleChannel( Registries.getLocation( "main" ), ()->PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals );
		CHANNEL.registerMessage( 0, TankEntity.TankAttackMessage.class, TankEntity.TankAttackMessage::encode, TankEntity.TankAttackMessage::new, TankEntity.TankAttackMessage::handle );
		CHANNEL.registerMessage( 1, LootProgressManager.ProgressMessage.class, LootProgressManager.ProgressMessage::encode, LootProgressManager.ProgressMessage::new, LootProgressManager.ProgressMessage::handle );
	}
}

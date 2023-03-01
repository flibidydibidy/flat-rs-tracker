package com.flatrstracker;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Flat RS Tracker"
)
public class FlatRsTrackerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private FlatRsTrackerConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Flat RS Tracker started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Flat RS Tracker stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Flat RS Tracker says " + config.greeting(), null);
		}
	}

	@Provides
	FlatRsTrackerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(FlatRsTrackerConfig.class);
	}
}

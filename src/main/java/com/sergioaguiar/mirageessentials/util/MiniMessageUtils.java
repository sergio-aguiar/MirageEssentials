package com.sergioaguiar.mirageessentials.util;

import com.cobblemon.mod.common.pokemon.Gender;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.sergioaguiar.mirageessentials.config.chatparser.minimessage.ChatMiniMessage;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public class MiniMessageUtils
{
    private static final MiniMessage MINIMESSAGE_INSTANCE = MiniMessage.miniMessage();

    private MiniMessageUtils() {}

    public static MiniMessage get()
    {
        return MINIMESSAGE_INSTANCE;
    }

    public static TagResolver getPokeInfoTagResolver(Pokemon pokemon)
    {
        String species = pokemon.getSpecies().getName();
        String name = (pokemon.getNickname() == null || pokemon.getNickname().getLiteralString() == null) 
                            ? species : pokemon.getNickname().getLiteralString();
        String title = pokemon.getActiveMark() == null ? "" : pokemon.getActiveMark().getTitle();
        String caughtBall = pokemon.getCaughtBall().item.getName().getString();

        return TagResolver.builder()
                .resolver(Placeholder.unparsed(ChatMiniMessage.NAME_TEMPLATE_STRING, name))
                .resolver(Placeholder.unparsed(ChatMiniMessage.TITLE_TEMPLATE_STRING, title))
                .resolver(getGenderResolver(pokemon.getGender()))
                .resolver(Placeholder.unparsed(ChatMiniMessage.CAUGHT_BALL_TEMPLATE_STRING, caughtBall))
                .build();
    }

    private static TagResolver.Single getGenderResolver(Gender gender)
    {
        String template = switch (gender)
        {
            case MALE -> ChatMiniMessage.getMaleGenderTemplate();
            case FEMALE -> ChatMiniMessage.getFemaleGenderTemplate();
            default -> ChatMiniMessage.getGenderlessGenderTemplate();
        };

        return Placeholder.parsed(ChatMiniMessage.GENDER_TEMPLATE_STRING, template);
    }
}

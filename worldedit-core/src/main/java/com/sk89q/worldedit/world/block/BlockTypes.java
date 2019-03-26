/*
 * WorldEdit, a Minecraft world manipulation toolkit
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldEdit team and contributors
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.worldedit.world.block;

import static com.google.common.base.Preconditions.checkArgument;

import com.boydti.fawe.Fawe;
import com.boydti.fawe.command.SuggestInputParseException;
import com.boydti.fawe.util.MathMan;
import com.boydti.fawe.util.ReflectionUtils;
import com.boydti.fawe.util.StringMan;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extension.input.InputParseException;
import com.sk89q.worldedit.extension.platform.Capability;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.function.mask.SingleBlockTypeMask;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.registry.state.AbstractProperty;
import com.sk89q.worldedit.registry.state.Property;
import com.sk89q.worldedit.registry.state.PropertyKey;
import com.sk89q.worldedit.util.Direction;
import com.sk89q.worldedit.world.item.ItemType;
import com.sk89q.worldedit.world.item.ItemTypes;
import com.sk89q.worldedit.world.registry.BlockMaterial;
import com.sk89q.worldedit.world.registry.BundledBlockData;
import com.sk89q.worldedit.world.registry.LegacyMapper;
import it.unimi.dsi.fastutil.ints.IntCollections;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stores a list of common Block String IDs.
 */
@SuppressWarnings("deprecation")
public class BlockTypes{
    /*
     -----------------------------------------------------
        Replaced at runtime by the block registry
     -----------------------------------------------------
     */
	public static final BlockType __RESERVED__ = register("minecraft:__reserved__");
	public static final BlockType ACACIA_BUTTON = register("minecraft:acacia_button", state -> state.with(state.getBlockType().getProperty("face"), "WALL").with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType ACACIA_DOOR = register("minecraft:acacia_door", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "lower").with(state.getBlockType().getProperty("hinge"), "left").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType ACACIA_FENCE = register("minecraft:acacia_fence", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType ACACIA_FENCE_GATE = register("minecraft:acacia_fence_gate", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("in_wall"), false).with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType ACACIA_LEAVES = register("minecraft:acacia_leaves", state -> state.with(state.getBlockType().getProperty("distance"), 7).with(state.getBlockType().getProperty("persistent"), false));
    public static final BlockType ACACIA_LOG = register("minecraft:acacia_log", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType ACACIA_PLANKS = register("minecraft:acacia_planks");
    public static final BlockType ACACIA_PRESSURE_PLATE = register("minecraft:acacia_pressure_plate", state -> state.with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType ACACIA_SAPLING = register("minecraft:acacia_sapling", state -> state.with(state.getBlockType().getProperty("stage"), 0));
    public static final BlockType ACACIA_SLAB = register("minecraft:acacia_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType ACACIA_STAIRS = register("minecraft:acacia_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType ACACIA_TRAPDOOR = register("minecraft:acacia_trapdoor", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false).with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType ACACIA_WOOD = register("minecraft:acacia_wood", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType ACTIVATOR_RAIL = register("minecraft:activator_rail", state -> state.with(state.getBlockType().getProperty("powered"), false).with(state.getBlockType().getProperty("shape"), "north_south"));
    public static final BlockType AIR = register("minecraft:air");
    public static final BlockType ALLIUM = register("minecraft:allium");
    public static final BlockType ANDESITE = register("minecraft:andesite");
    public static final BlockType ANVIL = register("minecraft:anvil", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType ATTACHED_MELON_STEM = register("minecraft:attached_melon_stem", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType ATTACHED_PUMPKIN_STEM = register("minecraft:attached_pumpkin_stem", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType AZURE_BLUET = register("minecraft:azure_bluet");
    public static final BlockType BARRIER = register("minecraft:barrier");
    public static final BlockType BEACON = register("minecraft:beacon");
    public static final BlockType BEDROCK = register("minecraft:bedrock");
    public static final BlockType BEETROOTS = register("minecraft:beetroots", state -> state.with(state.getBlockType().getProperty("age"), 0));
    public static final BlockType BIRCH_BUTTON = register("minecraft:birch_button", state -> state.with(state.getBlockType().getProperty("face"), "WALL").with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType BIRCH_DOOR = register("minecraft:birch_door", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "lower").with(state.getBlockType().getProperty("hinge"), "left").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType BIRCH_FENCE = register("minecraft:birch_fence", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType BIRCH_FENCE_GATE = register("minecraft:birch_fence_gate", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("in_wall"), false).with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType BIRCH_LEAVES = register("minecraft:birch_leaves", state -> state.with(state.getBlockType().getProperty("distance"), 7).with(state.getBlockType().getProperty("persistent"), false));
    public static final BlockType BIRCH_LOG = register("minecraft:birch_log", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType BIRCH_PLANKS = register("minecraft:birch_planks");
    public static final BlockType BIRCH_PRESSURE_PLATE = register("minecraft:birch_pressure_plate", state -> state.with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType BIRCH_SAPLING = register("minecraft:birch_sapling", state -> state.with(state.getBlockType().getProperty("stage"), 0));
    public static final BlockType BIRCH_SLAB = register("minecraft:birch_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType BIRCH_STAIRS = register("minecraft:birch_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType BIRCH_TRAPDOOR = register("minecraft:birch_trapdoor", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false).with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType BIRCH_WOOD = register("minecraft:birch_wood", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType BLACK_BANNER = register("minecraft:black_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType BLACK_BED = register("minecraft:black_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType BLACK_CARPET = register("minecraft:black_carpet");
    public static final BlockType BLACK_CONCRETE = register("minecraft:black_concrete");
    public static final BlockType BLACK_CONCRETE_POWDER = register("minecraft:black_concrete_powder");
    public static final BlockType BLACK_GLAZED_TERRACOTTA = register("minecraft:black_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType BLACK_SHULKER_BOX = register("minecraft:black_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType BLACK_STAINED_GLASS = register("minecraft:black_stained_glass");
    public static final BlockType BLACK_STAINED_GLASS_PANE = register("minecraft:black_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType BLACK_TERRACOTTA = register("minecraft:black_terracotta");
    public static final BlockType BLACK_WALL_BANNER = register("minecraft:black_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType BLACK_WOOL = register("minecraft:black_wool");
    public static final BlockType BLUE_BANNER = register("minecraft:blue_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType BLUE_BED = register("minecraft:blue_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType BLUE_CARPET = register("minecraft:blue_carpet");
    public static final BlockType BLUE_CONCRETE = register("minecraft:blue_concrete");
    public static final BlockType BLUE_CONCRETE_POWDER = register("minecraft:blue_concrete_powder");
    public static final BlockType BLUE_GLAZED_TERRACOTTA = register("minecraft:blue_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType BLUE_ICE = register("minecraft:blue_ice");
    public static final BlockType BLUE_ORCHID = register("minecraft:blue_orchid");
    public static final BlockType BLUE_SHULKER_BOX = register("minecraft:blue_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType BLUE_STAINED_GLASS = register("minecraft:blue_stained_glass");
    public static final BlockType BLUE_STAINED_GLASS_PANE = register("minecraft:blue_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType BLUE_TERRACOTTA = register("minecraft:blue_terracotta");
    public static final BlockType BLUE_WALL_BANNER = register("minecraft:blue_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType BLUE_WOOL = register("minecraft:blue_wool");
    public static final BlockType BONE_BLOCK = register("minecraft:bone_block", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType BOOKSHELF = register("minecraft:bookshelf");
    public static final BlockType BRAIN_CORAL = register("minecraft:brain_coral", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType BRAIN_CORAL_BLOCK = register("minecraft:brain_coral_block");
    public static final BlockType BRAIN_CORAL_FAN = register("minecraft:brain_coral_fan", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType BRAIN_CORAL_WALL_FAN = register("minecraft:brain_coral_wall_fan", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType BREWING_STAND = register("minecraft:brewing_stand", state -> state.with(state.getBlockType().getProperty("has_bottle_0"), false).with(state.getBlockType().getProperty("has_bottle_1"), false).with(state.getBlockType().getProperty("has_bottle_2"), false));
    public static final BlockType BRICK_SLAB = register("minecraft:brick_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType BRICK_STAIRS = register("minecraft:brick_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType BRICKS = register("minecraft:bricks");
    public static final BlockType BROWN_BANNER = register("minecraft:brown_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType BROWN_BED = register("minecraft:brown_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType BROWN_CARPET = register("minecraft:brown_carpet");
    public static final BlockType BROWN_CONCRETE = register("minecraft:brown_concrete");
    public static final BlockType BROWN_CONCRETE_POWDER = register("minecraft:brown_concrete_powder");
    public static final BlockType BROWN_GLAZED_TERRACOTTA = register("minecraft:brown_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType BROWN_MUSHROOM = register("minecraft:brown_mushroom");
    public static final BlockType BROWN_MUSHROOM_BLOCK = register("minecraft:brown_mushroom_block", state -> state.with(state.getBlockType().getProperty("down"), true).with(state.getBlockType().getProperty("east"), true).with(state.getBlockType().getProperty("north"), true).with(state.getBlockType().getProperty("south"), true).with(state.getBlockType().getProperty("up"), true).with(state.getBlockType().getProperty("west"), true));
    public static final BlockType BROWN_SHULKER_BOX = register("minecraft:brown_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType BROWN_STAINED_GLASS = register("minecraft:brown_stained_glass");
    public static final BlockType BROWN_STAINED_GLASS_PANE = register("minecraft:brown_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType BROWN_TERRACOTTA = register("minecraft:brown_terracotta");
    public static final BlockType BROWN_WALL_BANNER = register("minecraft:brown_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType BROWN_WOOL = register("minecraft:brown_wool");
    public static final BlockType BUBBLE_COLUMN = register("minecraft:bubble_column", state -> state.with(state.getBlockType().getProperty("drag"), true));
    public static final BlockType BUBBLE_CORAL = register("minecraft:bubble_coral", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType BUBBLE_CORAL_BLOCK = register("minecraft:bubble_coral_block");
    public static final BlockType BUBBLE_CORAL_FAN = register("minecraft:bubble_coral_fan", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType BUBBLE_CORAL_WALL_FAN = register("minecraft:bubble_coral_wall_fan", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType CACTUS = register("minecraft:cactus", state -> state.with(state.getBlockType().getProperty("age"), 0));
    public static final BlockType CAKE = register("minecraft:cake", state -> state.with(state.getBlockType().getProperty("bites"), 0));
    public static final BlockType CARROTS = register("minecraft:carrots", state -> state.with(state.getBlockType().getProperty("age"), 0));
    public static final BlockType CARVED_PUMPKIN = register("minecraft:carved_pumpkin", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType CAULDRON = register("minecraft:cauldron", state -> state.with(state.getBlockType().getProperty("level"), 0));
    public static final BlockType CAVE_AIR = register("minecraft:cave_air");
    public static final BlockType CHAIN_COMMAND_BLOCK = register("minecraft:chain_command_block", state -> state.with(state.getBlockType().getProperty("conditional"), false).with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType CHEST = register("minecraft:chest", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("type"), "SINGLE").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType CHIPPED_ANVIL = register("minecraft:chipped_anvil", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType CHISELED_QUARTZ_BLOCK = register("minecraft:chiseled_quartz_block");
    public static final BlockType CHISELED_RED_SANDSTONE = register("minecraft:chiseled_red_sandstone");
    public static final BlockType CHISELED_SANDSTONE = register("minecraft:chiseled_sandstone");
    public static final BlockType CHISELED_STONE_BRICKS = register("minecraft:chiseled_stone_bricks");
    public static final BlockType CHORUS_FLOWER = register("minecraft:chorus_flower", state -> state.with(state.getBlockType().getProperty("age"), 0));
    public static final BlockType CHORUS_PLANT = register("minecraft:chorus_plant", state -> state.with(state.getBlockType().getProperty("down"), false).with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("up"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType CLAY = register("minecraft:clay");
    public static final BlockType COAL_BLOCK = register("minecraft:coal_block");
    public static final BlockType COAL_ORE = register("minecraft:coal_ore");
    public static final BlockType COARSE_DIRT = register("minecraft:coarse_dirt");
    public static final BlockType COBBLESTONE = register("minecraft:cobblestone");
    public static final BlockType COBBLESTONE_SLAB = register("minecraft:cobblestone_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType COBBLESTONE_STAIRS = register("minecraft:cobblestone_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType COBBLESTONE_WALL = register("minecraft:cobblestone_wall", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("up"), true).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType COBWEB = register("minecraft:cobweb");
    public static final BlockType COCOA = register("minecraft:cocoa", state -> state.with(state.getBlockType().getProperty("age"), 0).with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType COMMAND_BLOCK = register("minecraft:command_block", state -> state.with(state.getBlockType().getProperty("conditional"), false).with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType COMPARATOR = register("minecraft:comparator", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("mode"), "compare").with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType CONDUIT = register("minecraft:conduit", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType CRACKED_STONE_BRICKS = register("minecraft:cracked_stone_bricks");
    public static final BlockType CRAFTING_TABLE = register("minecraft:crafting_table");
    public static final BlockType CREEPER_HEAD = register("minecraft:creeper_head", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType CREEPER_WALL_HEAD = register("minecraft:creeper_wall_head", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType CUT_RED_SANDSTONE = register("minecraft:cut_red_sandstone");
    public static final BlockType CUT_SANDSTONE = register("minecraft:cut_sandstone");
    public static final BlockType CYAN_BANNER = register("minecraft:cyan_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType CYAN_BED = register("minecraft:cyan_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType CYAN_CARPET = register("minecraft:cyan_carpet");
    public static final BlockType CYAN_CONCRETE = register("minecraft:cyan_concrete");
    public static final BlockType CYAN_CONCRETE_POWDER = register("minecraft:cyan_concrete_powder");
    public static final BlockType CYAN_GLAZED_TERRACOTTA = register("minecraft:cyan_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType CYAN_SHULKER_BOX = register("minecraft:cyan_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType CYAN_STAINED_GLASS = register("minecraft:cyan_stained_glass");
    public static final BlockType CYAN_STAINED_GLASS_PANE = register("minecraft:cyan_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType CYAN_TERRACOTTA = register("minecraft:cyan_terracotta");
    public static final BlockType CYAN_WALL_BANNER = register("minecraft:cyan_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType CYAN_WOOL = register("minecraft:cyan_wool");
    public static final BlockType DAMAGED_ANVIL = register("minecraft:damaged_anvil", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType DANDELION = register("minecraft:dandelion");
    public static final BlockType DARK_OAK_BUTTON = register("minecraft:dark_oak_button", state -> state.with(state.getBlockType().getProperty("face"), "WALL").with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType DARK_OAK_DOOR = register("minecraft:dark_oak_door", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "lower").with(state.getBlockType().getProperty("hinge"), "left").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType DARK_OAK_FENCE = register("minecraft:dark_oak_fence", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType DARK_OAK_FENCE_GATE = register("minecraft:dark_oak_fence_gate", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("in_wall"), false).with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType DARK_OAK_LEAVES = register("minecraft:dark_oak_leaves", state -> state.with(state.getBlockType().getProperty("distance"), 7).with(state.getBlockType().getProperty("persistent"), false));
    public static final BlockType DARK_OAK_LOG = register("minecraft:dark_oak_log", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType DARK_OAK_PLANKS = register("minecraft:dark_oak_planks");
    public static final BlockType DARK_OAK_PRESSURE_PLATE = register("minecraft:dark_oak_pressure_plate", state -> state.with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType DARK_OAK_SAPLING = register("minecraft:dark_oak_sapling", state -> state.with(state.getBlockType().getProperty("stage"), 0));
    public static final BlockType DARK_OAK_SLAB = register("minecraft:dark_oak_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType DARK_OAK_STAIRS = register("minecraft:dark_oak_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType DARK_OAK_TRAPDOOR = register("minecraft:dark_oak_trapdoor", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false).with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType DARK_OAK_WOOD = register("minecraft:dark_oak_wood", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType DARK_PRISMARINE = register("minecraft:dark_prismarine");
    public static final BlockType DARK_PRISMARINE_SLAB = register("minecraft:dark_prismarine_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType DARK_PRISMARINE_STAIRS = register("minecraft:dark_prismarine_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType DAYLIGHT_DETECTOR = register("minecraft:daylight_detector", state -> state.with(state.getBlockType().getProperty("inverted"), false).with(state.getBlockType().getProperty("power"), 0));
    public static final BlockType DEAD_BRAIN_CORAL = register("minecraft:dead_brain_coral", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_BRAIN_CORAL_BLOCK = register("minecraft:dead_brain_coral_block");
    public static final BlockType DEAD_BRAIN_CORAL_FAN = register("minecraft:dead_brain_coral_fan", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_BRAIN_CORAL_WALL_FAN = register("minecraft:dead_brain_coral_wall_fan", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_BUBBLE_CORAL = register("minecraft:dead_bubble_coral", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_BUBBLE_CORAL_BLOCK = register("minecraft:dead_bubble_coral_block");
    public static final BlockType DEAD_BUBBLE_CORAL_FAN = register("minecraft:dead_bubble_coral_fan", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_BUBBLE_CORAL_WALL_FAN = register("minecraft:dead_bubble_coral_wall_fan", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_BUSH = register("minecraft:dead_bush");
    public static final BlockType DEAD_FIRE_CORAL = register("minecraft:dead_fire_coral", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_FIRE_CORAL_BLOCK = register("minecraft:dead_fire_coral_block");
    public static final BlockType DEAD_FIRE_CORAL_FAN = register("minecraft:dead_fire_coral_fan", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_FIRE_CORAL_WALL_FAN = register("minecraft:dead_fire_coral_wall_fan", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_HORN_CORAL = register("minecraft:dead_horn_coral", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_HORN_CORAL_BLOCK = register("minecraft:dead_horn_coral_block");
    public static final BlockType DEAD_HORN_CORAL_FAN = register("minecraft:dead_horn_coral_fan", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_HORN_CORAL_WALL_FAN = register("minecraft:dead_horn_coral_wall_fan", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_TUBE_CORAL = register("minecraft:dead_tube_coral", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_TUBE_CORAL_BLOCK = register("minecraft:dead_tube_coral_block");
    public static final BlockType DEAD_TUBE_CORAL_FAN = register("minecraft:dead_tube_coral_fan", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DEAD_TUBE_CORAL_WALL_FAN = register("minecraft:dead_tube_coral_wall_fan", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType DETECTOR_RAIL = register("minecraft:detector_rail", state -> state.with(state.getBlockType().getProperty("powered"), false).with(state.getBlockType().getProperty("shape"), "north_south"));
    public static final BlockType DIAMOND_BLOCK = register("minecraft:diamond_block");
    public static final BlockType DIAMOND_ORE = register("minecraft:diamond_ore");
    public static final BlockType DIORITE = register("minecraft:diorite");
    public static final BlockType DIRT = register("minecraft:dirt");
    public static final BlockType DISPENSER = register("minecraft:dispenser", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("triggered"), false));
    public static final BlockType DRAGON_EGG = register("minecraft:dragon_egg");
    public static final BlockType DRAGON_HEAD = register("minecraft:dragon_head", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType DRAGON_WALL_HEAD = register("minecraft:dragon_wall_head", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType DRIED_KELP_BLOCK = register("minecraft:dried_kelp_block");
    public static final BlockType DROPPER = register("minecraft:dropper", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("triggered"), false));
    public static final BlockType EMERALD_BLOCK = register("minecraft:emerald_block");
    public static final BlockType EMERALD_ORE = register("minecraft:emerald_ore");
    public static final BlockType ENCHANTING_TABLE = register("minecraft:enchanting_table");
    public static final BlockType END_GATEWAY = register("minecraft:end_gateway");
    public static final BlockType END_PORTAL = register("minecraft:end_portal");
    public static final BlockType END_PORTAL_FRAME = register("minecraft:end_portal_frame", state -> state.with(state.getBlockType().getProperty("eye"), false).with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType END_ROD = register("minecraft:end_rod", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType END_STONE = register("minecraft:end_stone");
    public static final BlockType END_STONE_BRICKS = register("minecraft:end_stone_bricks");
    public static final BlockType ENDER_CHEST = register("minecraft:ender_chest", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType FARMLAND = register("minecraft:farmland", state -> state.with(state.getBlockType().getProperty("moisture"), 0));
    public static final BlockType FERN = register("minecraft:fern");
    public static final BlockType FIRE = register("minecraft:fire", state -> state.with(state.getBlockType().getProperty("age"), 0).with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("up"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType FIRE_CORAL = register("minecraft:fire_coral", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType FIRE_CORAL_BLOCK = register("minecraft:fire_coral_block");
    public static final BlockType FIRE_CORAL_FAN = register("minecraft:fire_coral_fan", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType FIRE_CORAL_WALL_FAN = register("minecraft:fire_coral_wall_fan", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType FLOWER_POT = register("minecraft:flower_pot");
    public static final BlockType FROSTED_ICE = register("minecraft:frosted_ice", state -> state.with(state.getBlockType().getProperty("age"), 0));
    public static final BlockType FURNACE = register("minecraft:furnace", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("lit"), false));
    public static final BlockType GLASS = register("minecraft:glass");
    public static final BlockType GLASS_PANE = register("minecraft:glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType GLOWSTONE = register("minecraft:glowstone");
    public static final BlockType GOLD_BLOCK = register("minecraft:gold_block");
    public static final BlockType GOLD_ORE = register("minecraft:gold_ore");
    public static final BlockType GRANITE = register("minecraft:granite");
    public static final BlockType GRASS = register("minecraft:grass");
    public static final BlockType GRASS_BLOCK = register("minecraft:grass_block", state -> state.with(state.getBlockType().getProperty("snowy"), false));
    public static final BlockType GRASS_PATH = register("minecraft:grass_path");
    public static final BlockType GRAVEL = register("minecraft:gravel");
    public static final BlockType GRAY_BANNER = register("minecraft:gray_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType GRAY_BED = register("minecraft:gray_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType GRAY_CARPET = register("minecraft:gray_carpet");
    public static final BlockType GRAY_CONCRETE = register("minecraft:gray_concrete");
    public static final BlockType GRAY_CONCRETE_POWDER = register("minecraft:gray_concrete_powder");
    public static final BlockType GRAY_GLAZED_TERRACOTTA = register("minecraft:gray_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType GRAY_SHULKER_BOX = register("minecraft:gray_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType GRAY_STAINED_GLASS = register("minecraft:gray_stained_glass");
    public static final BlockType GRAY_STAINED_GLASS_PANE = register("minecraft:gray_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType GRAY_TERRACOTTA = register("minecraft:gray_terracotta");
    public static final BlockType GRAY_WALL_BANNER = register("minecraft:gray_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType GRAY_WOOL = register("minecraft:gray_wool");
    public static final BlockType GREEN_BANNER = register("minecraft:green_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType GREEN_BED = register("minecraft:green_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType GREEN_CARPET = register("minecraft:green_carpet");
    public static final BlockType GREEN_CONCRETE = register("minecraft:green_concrete");
    public static final BlockType GREEN_CONCRETE_POWDER = register("minecraft:green_concrete_powder");
    public static final BlockType GREEN_GLAZED_TERRACOTTA = register("minecraft:green_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType GREEN_SHULKER_BOX = register("minecraft:green_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType GREEN_STAINED_GLASS = register("minecraft:green_stained_glass");
    public static final BlockType GREEN_STAINED_GLASS_PANE = register("minecraft:green_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType GREEN_TERRACOTTA = register("minecraft:green_terracotta");
    public static final BlockType GREEN_WALL_BANNER = register("minecraft:green_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType GREEN_WOOL = register("minecraft:green_wool");
    public static final BlockType HAY_BLOCK = register("minecraft:hay_block", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType HEAVY_WEIGHTED_PRESSURE_PLATE = register("minecraft:heavy_weighted_pressure_plate", state -> state.with(state.getBlockType().getProperty("power"), 0));
    public static final BlockType HOPPER = register("minecraft:hopper", state -> state.with(state.getBlockType().getProperty("enabled"), true).with(state.getBlockType().getProperty("facing"), Direction.DOWN));
    public static final BlockType HORN_CORAL = register("minecraft:horn_coral", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType HORN_CORAL_BLOCK = register("minecraft:horn_coral_block");
    public static final BlockType HORN_CORAL_FAN = register("minecraft:horn_coral_fan", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType HORN_CORAL_WALL_FAN = register("minecraft:horn_coral_wall_fan", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType ICE = register("minecraft:ice");
    public static final BlockType INFESTED_CHISELED_STONE_BRICKS = register("minecraft:infested_chiseled_stone_bricks");
    public static final BlockType INFESTED_COBBLESTONE = register("minecraft:infested_cobblestone");
    public static final BlockType INFESTED_CRACKED_STONE_BRICKS = register("minecraft:infested_cracked_stone_bricks");
    public static final BlockType INFESTED_MOSSY_STONE_BRICKS = register("minecraft:infested_mossy_stone_bricks");
    public static final BlockType INFESTED_STONE = register("minecraft:infested_stone");
    public static final BlockType INFESTED_STONE_BRICKS = register("minecraft:infested_stone_bricks");
    public static final BlockType IRON_BARS = register("minecraft:iron_bars", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType IRON_BLOCK = register("minecraft:iron_block");
    public static final BlockType IRON_DOOR = register("minecraft:iron_door", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "lower").with(state.getBlockType().getProperty("hinge"), "left").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType IRON_ORE = register("minecraft:iron_ore");
    public static final BlockType IRON_TRAPDOOR = register("minecraft:iron_trapdoor", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false).with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType JACK_O_LANTERN = register("minecraft:jack_o_lantern", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType JUKEBOX = register("minecraft:jukebox", state -> state.with(state.getBlockType().getProperty("has_record"), false));
    public static final BlockType JUNGLE_BUTTON = register("minecraft:jungle_button", state -> state.with(state.getBlockType().getProperty("face"), "WALL").with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType JUNGLE_DOOR = register("minecraft:jungle_door", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "lower").with(state.getBlockType().getProperty("hinge"), "left").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType JUNGLE_FENCE = register("minecraft:jungle_fence", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType JUNGLE_FENCE_GATE = register("minecraft:jungle_fence_gate", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("in_wall"), false).with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType JUNGLE_LEAVES = register("minecraft:jungle_leaves", state -> state.with(state.getBlockType().getProperty("distance"), 7).with(state.getBlockType().getProperty("persistent"), false));
    public static final BlockType JUNGLE_LOG = register("minecraft:jungle_log", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType JUNGLE_PLANKS = register("minecraft:jungle_planks");
    public static final BlockType JUNGLE_PRESSURE_PLATE = register("minecraft:jungle_pressure_plate", state -> state.with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType JUNGLE_SAPLING = register("minecraft:jungle_sapling", state -> state.with(state.getBlockType().getProperty("stage"), 0));
    public static final BlockType JUNGLE_SLAB = register("minecraft:jungle_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType JUNGLE_STAIRS = register("minecraft:jungle_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType JUNGLE_TRAPDOOR = register("minecraft:jungle_trapdoor", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false).with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType JUNGLE_WOOD = register("minecraft:jungle_wood", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType KELP = register("minecraft:kelp", state -> state.with(state.getBlockType().getProperty("age"), 0));
    public static final BlockType KELP_PLANT = register("minecraft:kelp_plant");
    public static final BlockType LADDER = register("minecraft:ladder", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType LAPIS_BLOCK = register("minecraft:lapis_block");
    public static final BlockType LAPIS_ORE = register("minecraft:lapis_ore");
    public static final BlockType LARGE_FERN = register("minecraft:large_fern", state -> state.with(state.getBlockType().getProperty("half"), "lower"));
    public static final BlockType LAVA = register("minecraft:lava", state -> state.with(state.getBlockType().getProperty("level"), 0));
    public static final BlockType LEVER = register("minecraft:lever", state -> state.with(state.getBlockType().getProperty("face"), "WALL").with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType LIGHT_BLUE_BANNER = register("minecraft:light_blue_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType LIGHT_BLUE_BED = register("minecraft:light_blue_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType LIGHT_BLUE_CARPET = register("minecraft:light_blue_carpet");
    public static final BlockType LIGHT_BLUE_CONCRETE = register("minecraft:light_blue_concrete");
    public static final BlockType LIGHT_BLUE_CONCRETE_POWDER = register("minecraft:light_blue_concrete_powder");
    public static final BlockType LIGHT_BLUE_GLAZED_TERRACOTTA = register("minecraft:light_blue_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType LIGHT_BLUE_SHULKER_BOX = register("minecraft:light_blue_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType LIGHT_BLUE_STAINED_GLASS = register("minecraft:light_blue_stained_glass");
    public static final BlockType LIGHT_BLUE_STAINED_GLASS_PANE = register("minecraft:light_blue_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType LIGHT_BLUE_TERRACOTTA = register("minecraft:light_blue_terracotta");
    public static final BlockType LIGHT_BLUE_WALL_BANNER = register("minecraft:light_blue_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType LIGHT_BLUE_WOOL = register("minecraft:light_blue_wool");
    public static final BlockType LIGHT_GRAY_BANNER = register("minecraft:light_gray_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType LIGHT_GRAY_BED = register("minecraft:light_gray_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType LIGHT_GRAY_CARPET = register("minecraft:light_gray_carpet");
    public static final BlockType LIGHT_GRAY_CONCRETE = register("minecraft:light_gray_concrete");
    public static final BlockType LIGHT_GRAY_CONCRETE_POWDER = register("minecraft:light_gray_concrete_powder");
    public static final BlockType LIGHT_GRAY_GLAZED_TERRACOTTA = register("minecraft:light_gray_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType LIGHT_GRAY_SHULKER_BOX = register("minecraft:light_gray_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType LIGHT_GRAY_STAINED_GLASS = register("minecraft:light_gray_stained_glass");
    public static final BlockType LIGHT_GRAY_STAINED_GLASS_PANE = register("minecraft:light_gray_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType LIGHT_GRAY_TERRACOTTA = register("minecraft:light_gray_terracotta");
    public static final BlockType LIGHT_GRAY_WALL_BANNER = register("minecraft:light_gray_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType LIGHT_GRAY_WOOL = register("minecraft:light_gray_wool");
    public static final BlockType LIGHT_WEIGHTED_PRESSURE_PLATE = register("minecraft:light_weighted_pressure_plate", state -> state.with(state.getBlockType().getProperty("power"), 0));
    public static final BlockType LILAC = register("minecraft:lilac", state -> state.with(state.getBlockType().getProperty("half"), "lower"));
    public static final BlockType LILY_PAD = register("minecraft:lily_pad");
    public static final BlockType LIME_BANNER = register("minecraft:lime_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType LIME_BED = register("minecraft:lime_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType LIME_CARPET = register("minecraft:lime_carpet");
    public static final BlockType LIME_CONCRETE = register("minecraft:lime_concrete");
    public static final BlockType LIME_CONCRETE_POWDER = register("minecraft:lime_concrete_powder");
    public static final BlockType LIME_GLAZED_TERRACOTTA = register("minecraft:lime_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType LIME_SHULKER_BOX = register("minecraft:lime_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType LIME_STAINED_GLASS = register("minecraft:lime_stained_glass");
    public static final BlockType LIME_STAINED_GLASS_PANE = register("minecraft:lime_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType LIME_TERRACOTTA = register("minecraft:lime_terracotta");
    public static final BlockType LIME_WALL_BANNER = register("minecraft:lime_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType LIME_WOOL = register("minecraft:lime_wool");
    public static final BlockType MAGENTA_BANNER = register("minecraft:magenta_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType MAGENTA_BED = register("minecraft:magenta_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType MAGENTA_CARPET = register("minecraft:magenta_carpet");
    public static final BlockType MAGENTA_CONCRETE = register("minecraft:magenta_concrete");
    public static final BlockType MAGENTA_CONCRETE_POWDER = register("minecraft:magenta_concrete_powder");
    public static final BlockType MAGENTA_GLAZED_TERRACOTTA = register("minecraft:magenta_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType MAGENTA_SHULKER_BOX = register("minecraft:magenta_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType MAGENTA_STAINED_GLASS = register("minecraft:magenta_stained_glass");
    public static final BlockType MAGENTA_STAINED_GLASS_PANE = register("minecraft:magenta_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType MAGENTA_TERRACOTTA = register("minecraft:magenta_terracotta");
    public static final BlockType MAGENTA_WALL_BANNER = register("minecraft:magenta_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType MAGENTA_WOOL = register("minecraft:magenta_wool");
    public static final BlockType MAGMA_BLOCK = register("minecraft:magma_block");
    public static final BlockType MELON = register("minecraft:melon");
    public static final BlockType MELON_STEM = register("minecraft:melon_stem", state -> state.with(state.getBlockType().getProperty("age"), 0));
    public static final BlockType MOSSY_COBBLESTONE = register("minecraft:mossy_cobblestone");
    public static final BlockType MOSSY_COBBLESTONE_WALL = register("minecraft:mossy_cobblestone_wall", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("up"), true).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType MOSSY_STONE_BRICKS = register("minecraft:mossy_stone_bricks");
    public static final BlockType MOVING_PISTON = register("minecraft:moving_piston", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("type"), "normal"));
    public static final BlockType MUSHROOM_STEM = register("minecraft:mushroom_stem", state -> state.with(state.getBlockType().getProperty("down"), true).with(state.getBlockType().getProperty("east"), true).with(state.getBlockType().getProperty("north"), true).with(state.getBlockType().getProperty("south"), true).with(state.getBlockType().getProperty("up"), true).with(state.getBlockType().getProperty("west"), true));
    public static final BlockType MYCELIUM = register("minecraft:mycelium", state -> state.with(state.getBlockType().getProperty("snowy"), false));
    public static final BlockType NETHER_BRICK_FENCE = register("minecraft:nether_brick_fence", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType NETHER_BRICK_SLAB = register("minecraft:nether_brick_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType NETHER_BRICK_STAIRS = register("minecraft:nether_brick_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType NETHER_BRICKS = register("minecraft:nether_bricks");
    public static final BlockType NETHER_PORTAL = register("minecraft:nether_portal", state -> state.with(state.getBlockType().getProperty("axis"), "x"));
    public static final BlockType NETHER_QUARTZ_ORE = register("minecraft:nether_quartz_ore");
    public static final BlockType NETHER_WART = register("minecraft:nether_wart", state -> state.with(state.getBlockType().getProperty("age"), 0));
    public static final BlockType NETHER_WART_BLOCK = register("minecraft:nether_wart_block");
    public static final BlockType NETHERRACK = register("minecraft:netherrack");
    public static final BlockType NOTE_BLOCK = register("minecraft:note_block", state -> state.with(state.getBlockType().getProperty("instrument"), "HARP").with(state.getBlockType().getProperty("note"), 0).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType OAK_BUTTON = register("minecraft:oak_button", state -> state.with(state.getBlockType().getProperty("face"), "WALL").with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType OAK_DOOR = register("minecraft:oak_door", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "lower").with(state.getBlockType().getProperty("hinge"), "left").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType OAK_FENCE = register("minecraft:oak_fence", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType OAK_FENCE_GATE = register("minecraft:oak_fence_gate", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("in_wall"), false).with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType OAK_LEAVES = register("minecraft:oak_leaves", state -> state.with(state.getBlockType().getProperty("distance"), 7).with(state.getBlockType().getProperty("persistent"), false));
    public static final BlockType OAK_LOG = register("minecraft:oak_log", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType OAK_PLANKS = register("minecraft:oak_planks");
    public static final BlockType OAK_PRESSURE_PLATE = register("minecraft:oak_pressure_plate", state -> state.with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType OAK_SAPLING = register("minecraft:oak_sapling", state -> state.with(state.getBlockType().getProperty("stage"), 0));
    public static final BlockType OAK_SLAB = register("minecraft:oak_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType OAK_STAIRS = register("minecraft:oak_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType OAK_TRAPDOOR = register("minecraft:oak_trapdoor", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false).with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType OAK_WOOD = register("minecraft:oak_wood", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType OBSERVER = register("minecraft:observer", state -> state.with(state.getBlockType().getProperty("facing"), Direction.SOUTH).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType OBSIDIAN = register("minecraft:obsidian");
    public static final BlockType ORANGE_BANNER = register("minecraft:orange_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType ORANGE_BED = register("minecraft:orange_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType ORANGE_CARPET = register("minecraft:orange_carpet");
    public static final BlockType ORANGE_CONCRETE = register("minecraft:orange_concrete");
    public static final BlockType ORANGE_CONCRETE_POWDER = register("minecraft:orange_concrete_powder");
    public static final BlockType ORANGE_GLAZED_TERRACOTTA = register("minecraft:orange_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType ORANGE_SHULKER_BOX = register("minecraft:orange_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType ORANGE_STAINED_GLASS = register("minecraft:orange_stained_glass");
    public static final BlockType ORANGE_STAINED_GLASS_PANE = register("minecraft:orange_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType ORANGE_TERRACOTTA = register("minecraft:orange_terracotta");
    public static final BlockType ORANGE_TULIP = register("minecraft:orange_tulip");
    public static final BlockType ORANGE_WALL_BANNER = register("minecraft:orange_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType ORANGE_WOOL = register("minecraft:orange_wool");
    public static final BlockType OXEYE_DAISY = register("minecraft:oxeye_daisy");
    public static final BlockType PACKED_ICE = register("minecraft:packed_ice");
    public static final BlockType PEONY = register("minecraft:peony", state -> state.with(state.getBlockType().getProperty("half"), "lower"));
    public static final BlockType PETRIFIED_OAK_SLAB = register("minecraft:petrified_oak_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType PINK_BANNER = register("minecraft:pink_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType PINK_BED = register("minecraft:pink_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType PINK_CARPET = register("minecraft:pink_carpet");
    public static final BlockType PINK_CONCRETE = register("minecraft:pink_concrete");
    public static final BlockType PINK_CONCRETE_POWDER = register("minecraft:pink_concrete_powder");
    public static final BlockType PINK_GLAZED_TERRACOTTA = register("minecraft:pink_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType PINK_SHULKER_BOX = register("minecraft:pink_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType PINK_STAINED_GLASS = register("minecraft:pink_stained_glass");
    public static final BlockType PINK_STAINED_GLASS_PANE = register("minecraft:pink_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType PINK_TERRACOTTA = register("minecraft:pink_terracotta");
    public static final BlockType PINK_TULIP = register("minecraft:pink_tulip");
    public static final BlockType PINK_WALL_BANNER = register("minecraft:pink_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType PINK_WOOL = register("minecraft:pink_wool");
    public static final BlockType PISTON = register("minecraft:piston", state -> state.with(state.getBlockType().getProperty("extended"), false).with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType PISTON_HEAD = register("minecraft:piston_head", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("short"), false).with(state.getBlockType().getProperty("type"), "normal"));
    public static final BlockType PLAYER_HEAD = register("minecraft:player_head", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType PLAYER_WALL_HEAD = register("minecraft:player_wall_head", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType PODZOL = register("minecraft:podzol", state -> state.with(state.getBlockType().getProperty("snowy"), false));
    public static final BlockType POLISHED_ANDESITE = register("minecraft:polished_andesite");
    public static final BlockType POLISHED_DIORITE = register("minecraft:polished_diorite");
    public static final BlockType POLISHED_GRANITE = register("minecraft:polished_granite");
    public static final BlockType POPPY = register("minecraft:poppy");
    public static final BlockType POTATOES = register("minecraft:potatoes", state -> state.with(state.getBlockType().getProperty("age"), 0));
    public static final BlockType POTTED_ACACIA_SAPLING = register("minecraft:potted_acacia_sapling");
    public static final BlockType POTTED_ALLIUM = register("minecraft:potted_allium");
    public static final BlockType POTTED_AZURE_BLUET = register("minecraft:potted_azure_bluet");
    public static final BlockType POTTED_BIRCH_SAPLING = register("minecraft:potted_birch_sapling");
    public static final BlockType POTTED_BLUE_ORCHID = register("minecraft:potted_blue_orchid");
    public static final BlockType POTTED_BROWN_MUSHROOM = register("minecraft:potted_brown_mushroom");
    public static final BlockType POTTED_CACTUS = register("minecraft:potted_cactus");
    public static final BlockType POTTED_DANDELION = register("minecraft:potted_dandelion");
    public static final BlockType POTTED_DARK_OAK_SAPLING = register("minecraft:potted_dark_oak_sapling");
    public static final BlockType POTTED_DEAD_BUSH = register("minecraft:potted_dead_bush");
    public static final BlockType POTTED_FERN = register("minecraft:potted_fern");
    public static final BlockType POTTED_JUNGLE_SAPLING = register("minecraft:potted_jungle_sapling");
    public static final BlockType POTTED_OAK_SAPLING = register("minecraft:potted_oak_sapling");
    public static final BlockType POTTED_ORANGE_TULIP = register("minecraft:potted_orange_tulip");
    public static final BlockType POTTED_OXEYE_DAISY = register("minecraft:potted_oxeye_daisy");
    public static final BlockType POTTED_PINK_TULIP = register("minecraft:potted_pink_tulip");
    public static final BlockType POTTED_POPPY = register("minecraft:potted_poppy");
    public static final BlockType POTTED_RED_MUSHROOM = register("minecraft:potted_red_mushroom");
    public static final BlockType POTTED_RED_TULIP = register("minecraft:potted_red_tulip");
    public static final BlockType POTTED_SPRUCE_SAPLING = register("minecraft:potted_spruce_sapling");
    public static final BlockType POTTED_WHITE_TULIP = register("minecraft:potted_white_tulip");
    public static final BlockType POWERED_RAIL = register("minecraft:powered_rail", state -> state.with(state.getBlockType().getProperty("powered"), false).with(state.getBlockType().getProperty("shape"), "north_south"));
    public static final BlockType PRISMARINE = register("minecraft:prismarine");
    public static final BlockType PRISMARINE_BRICK_SLAB = register("minecraft:prismarine_brick_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType PRISMARINE_BRICK_STAIRS = register("minecraft:prismarine_brick_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType PRISMARINE_BRICKS = register("minecraft:prismarine_bricks");
    public static final BlockType PRISMARINE_SLAB = register("minecraft:prismarine_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType PRISMARINE_STAIRS = register("minecraft:prismarine_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType PUMPKIN = register("minecraft:pumpkin");
    public static final BlockType PUMPKIN_STEM = register("minecraft:pumpkin_stem", state -> state.with(state.getBlockType().getProperty("age"), 0));
    public static final BlockType PURPLE_BANNER = register("minecraft:purple_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType PURPLE_BED = register("minecraft:purple_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType PURPLE_CARPET = register("minecraft:purple_carpet");
    public static final BlockType PURPLE_CONCRETE = register("minecraft:purple_concrete");
    public static final BlockType PURPLE_CONCRETE_POWDER = register("minecraft:purple_concrete_powder");
    public static final BlockType PURPLE_GLAZED_TERRACOTTA = register("minecraft:purple_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType PURPLE_SHULKER_BOX = register("minecraft:purple_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType PURPLE_STAINED_GLASS = register("minecraft:purple_stained_glass");
    public static final BlockType PURPLE_STAINED_GLASS_PANE = register("minecraft:purple_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType PURPLE_TERRACOTTA = register("minecraft:purple_terracotta");
    public static final BlockType PURPLE_WALL_BANNER = register("minecraft:purple_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType PURPLE_WOOL = register("minecraft:purple_wool");
    public static final BlockType PURPUR_BLOCK = register("minecraft:purpur_block");
    public static final BlockType PURPUR_PILLAR = register("minecraft:purpur_pillar", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType PURPUR_SLAB = register("minecraft:purpur_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType PURPUR_STAIRS = register("minecraft:purpur_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType QUARTZ_BLOCK = register("minecraft:quartz_block");
    public static final BlockType QUARTZ_PILLAR = register("minecraft:quartz_pillar", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType QUARTZ_SLAB = register("minecraft:quartz_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType QUARTZ_STAIRS = register("minecraft:quartz_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType RAIL = register("minecraft:rail", state -> state.with(state.getBlockType().getProperty("shape"), "north_south"));
    public static final BlockType RED_BANNER = register("minecraft:red_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType RED_BED = register("minecraft:red_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType RED_CARPET = register("minecraft:red_carpet");
    public static final BlockType RED_CONCRETE = register("minecraft:red_concrete");
    public static final BlockType RED_CONCRETE_POWDER = register("minecraft:red_concrete_powder");
    public static final BlockType RED_GLAZED_TERRACOTTA = register("minecraft:red_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType RED_MUSHROOM = register("minecraft:red_mushroom");
    public static final BlockType RED_MUSHROOM_BLOCK = register("minecraft:red_mushroom_block", state -> state.with(state.getBlockType().getProperty("down"), true).with(state.getBlockType().getProperty("east"), true).with(state.getBlockType().getProperty("north"), true).with(state.getBlockType().getProperty("south"), true).with(state.getBlockType().getProperty("up"), true).with(state.getBlockType().getProperty("west"), true));
    public static final BlockType RED_NETHER_BRICKS = register("minecraft:red_nether_bricks");
    public static final BlockType RED_SAND = register("minecraft:red_sand");
    public static final BlockType RED_SANDSTONE = register("minecraft:red_sandstone");
    public static final BlockType RED_SANDSTONE_SLAB = register("minecraft:red_sandstone_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType RED_SANDSTONE_STAIRS = register("minecraft:red_sandstone_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType RED_SHULKER_BOX = register("minecraft:red_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType RED_STAINED_GLASS = register("minecraft:red_stained_glass");
    public static final BlockType RED_STAINED_GLASS_PANE = register("minecraft:red_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType RED_TERRACOTTA = register("minecraft:red_terracotta");
    public static final BlockType RED_TULIP = register("minecraft:red_tulip");
    public static final BlockType RED_WALL_BANNER = register("minecraft:red_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType RED_WOOL = register("minecraft:red_wool");
    public static final BlockType REDSTONE_BLOCK = register("minecraft:redstone_block");
    public static final BlockType REDSTONE_LAMP = register("minecraft:redstone_lamp", state -> state.with(state.getBlockType().getProperty("lit"), false));
    public static final BlockType REDSTONE_ORE = register("minecraft:redstone_ore", state -> state.with(state.getBlockType().getProperty("lit"), false));
    public static final BlockType REDSTONE_TORCH = register("minecraft:redstone_torch", state -> state.with(state.getBlockType().getProperty("lit"), true));
    public static final BlockType REDSTONE_WALL_TORCH = register("minecraft:redstone_wall_torch", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("lit"), true));
    public static final BlockType REDSTONE_WIRE = register("minecraft:redstone_wire", state -> state.with(state.getBlockType().getProperty("east"), "none").with(state.getBlockType().getProperty("north"), "none").with(state.getBlockType().getProperty("power"), 0).with(state.getBlockType().getProperty("south"), "none").with(state.getBlockType().getProperty("west"), "none"));
    public static final BlockType REPEATER = register("minecraft:repeater", state -> state.with(state.getBlockType().getProperty("delay"), 1).with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("locked"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType REPEATING_COMMAND_BLOCK = register("minecraft:repeating_command_block", state -> state.with(state.getBlockType().getProperty("conditional"), false).with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType ROSE_BUSH = register("minecraft:rose_bush", state -> state.with(state.getBlockType().getProperty("half"), "lower"));
    public static final BlockType SAND = register("minecraft:sand");
    public static final BlockType SANDSTONE = register("minecraft:sandstone");
    public static final BlockType SANDSTONE_SLAB = register("minecraft:sandstone_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType SANDSTONE_STAIRS = register("minecraft:sandstone_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType SEA_LANTERN = register("minecraft:sea_lantern");
    public static final BlockType SEA_PICKLE = register("minecraft:sea_pickle", state -> state.with(state.getBlockType().getProperty("pickles"), 1).with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType SEAGRASS = register("minecraft:seagrass");
    public static final BlockType SHULKER_BOX = register("minecraft:shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType SIGN = register("minecraft:sign", state -> state.with(state.getBlockType().getProperty("rotation"), 0).with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType SKELETON_SKULL = register("minecraft:skeleton_skull", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType SKELETON_WALL_SKULL = register("minecraft:skeleton_wall_skull", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType SLIME_BLOCK = register("minecraft:slime_block");
    public static final BlockType SMOOTH_QUARTZ = register("minecraft:smooth_quartz");
    public static final BlockType SMOOTH_RED_SANDSTONE = register("minecraft:smooth_red_sandstone");
    public static final BlockType SMOOTH_SANDSTONE = register("minecraft:smooth_sandstone");
    public static final BlockType SMOOTH_STONE = register("minecraft:smooth_stone");
    public static final BlockType SNOW = register("minecraft:snow", state -> state.with(state.getBlockType().getProperty("layers"), 1));
    public static final BlockType SNOW_BLOCK = register("minecraft:snow_block");
    public static final BlockType SOUL_SAND = register("minecraft:soul_sand");
    public static final BlockType SPAWNER = register("minecraft:spawner");
    public static final BlockType SPONGE = register("minecraft:sponge");
    public static final BlockType SPRUCE_BUTTON = register("minecraft:spruce_button", state -> state.with(state.getBlockType().getProperty("face"), "WALL").with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType SPRUCE_DOOR = register("minecraft:spruce_door", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "lower").with(state.getBlockType().getProperty("hinge"), "left").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType SPRUCE_FENCE = register("minecraft:spruce_fence", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType SPRUCE_FENCE_GATE = register("minecraft:spruce_fence_gate", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("in_wall"), false).with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType SPRUCE_LEAVES = register("minecraft:spruce_leaves", state -> state.with(state.getBlockType().getProperty("distance"), 7).with(state.getBlockType().getProperty("persistent"), false));
    public static final BlockType SPRUCE_LOG = register("minecraft:spruce_log", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType SPRUCE_PLANKS = register("minecraft:spruce_planks");
    public static final BlockType SPRUCE_PRESSURE_PLATE = register("minecraft:spruce_pressure_plate", state -> state.with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType SPRUCE_SAPLING = register("minecraft:spruce_sapling", state -> state.with(state.getBlockType().getProperty("stage"), 0));
    public static final BlockType SPRUCE_SLAB = register("minecraft:spruce_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType SPRUCE_STAIRS = register("minecraft:spruce_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType SPRUCE_TRAPDOOR = register("minecraft:spruce_trapdoor", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("open"), false).with(state.getBlockType().getProperty("powered"), false).with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType SPRUCE_WOOD = register("minecraft:spruce_wood", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STICKY_PISTON = register("minecraft:sticky_piston", state -> state.with(state.getBlockType().getProperty("extended"), false).with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType STONE = register("minecraft:stone");
    public static final BlockType STONE_BRICK_SLAB = register("minecraft:stone_brick_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType STONE_BRICK_STAIRS = register("minecraft:stone_brick_stairs", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("half"), "bottom").with(state.getBlockType().getProperty("shape"), "straight").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType STONE_BRICKS = register("minecraft:stone_bricks");
    public static final BlockType STONE_BUTTON = register("minecraft:stone_button", state -> state.with(state.getBlockType().getProperty("face"), "WALL").with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType STONE_PRESSURE_PLATE = register("minecraft:stone_pressure_plate", state -> state.with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType STONE_SLAB = register("minecraft:stone_slab", state -> state.with(state.getBlockType().getProperty("type"), "bottom").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType STRIPPED_ACACIA_LOG = register("minecraft:stripped_acacia_log", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STRIPPED_ACACIA_WOOD = register("minecraft:stripped_acacia_wood", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STRIPPED_BIRCH_LOG = register("minecraft:stripped_birch_log", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STRIPPED_BIRCH_WOOD = register("minecraft:stripped_birch_wood", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STRIPPED_DARK_OAK_LOG = register("minecraft:stripped_dark_oak_log", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STRIPPED_DARK_OAK_WOOD = register("minecraft:stripped_dark_oak_wood", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STRIPPED_JUNGLE_LOG = register("minecraft:stripped_jungle_log", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STRIPPED_JUNGLE_WOOD = register("minecraft:stripped_jungle_wood", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STRIPPED_OAK_LOG = register("minecraft:stripped_oak_log", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STRIPPED_OAK_WOOD = register("minecraft:stripped_oak_wood", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STRIPPED_SPRUCE_LOG = register("minecraft:stripped_spruce_log", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STRIPPED_SPRUCE_WOOD = register("minecraft:stripped_spruce_wood", state -> state.with(state.getBlockType().getProperty("axis"), "y"));
    public static final BlockType STRUCTURE_BLOCK = register("minecraft:structure_block", state -> state.with(state.getBlockType().getProperty("mode"), "SAVE"));
    public static final BlockType STRUCTURE_VOID = register("minecraft:structure_void");
    public static final BlockType SUGAR_CANE = register("minecraft:sugar_cane", state -> state.with(state.getBlockType().getProperty("age"), 0));
    public static final BlockType SUNFLOWER = register("minecraft:sunflower", state -> state.with(state.getBlockType().getProperty("half"), "lower"));
    public static final BlockType TALL_GRASS = register("minecraft:tall_grass", state -> state.with(state.getBlockType().getProperty("half"), "lower"));
    public static final BlockType TALL_SEAGRASS = register("minecraft:tall_seagrass", state -> state.with(state.getBlockType().getProperty("half"), "lower"));
    public static final BlockType TERRACOTTA = register("minecraft:terracotta");
    public static final BlockType TNT = register("minecraft:tnt", state -> state.with(state.getBlockType().getProperty("unstable"), false));
    public static final BlockType TORCH = register("minecraft:torch");
    public static final BlockType TRAPPED_CHEST = register("minecraft:trapped_chest", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("type"), "SINGLE").with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType TRIPWIRE = register("minecraft:tripwire", state -> state.with(state.getBlockType().getProperty("attached"), false).with(state.getBlockType().getProperty("disarmed"), false).with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("powered"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType TRIPWIRE_HOOK = register("minecraft:tripwire_hook", state -> state.with(state.getBlockType().getProperty("attached"), false).with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("powered"), false));
    public static final BlockType TUBE_CORAL = register("minecraft:tube_coral", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType TUBE_CORAL_BLOCK = register("minecraft:tube_coral_block");
    public static final BlockType TUBE_CORAL_FAN = register("minecraft:tube_coral_fan", state -> state.with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType TUBE_CORAL_WALL_FAN = register("minecraft:tube_coral_wall_fan", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), true));
    public static final BlockType TURTLE_EGG = register("minecraft:turtle_egg", state -> state.with(state.getBlockType().getProperty("eggs"), 1).with(state.getBlockType().getProperty("hatch"), 0));
    public static final BlockType VINE = register("minecraft:vine", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("up"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType VOID_AIR = register("minecraft:void_air");
    public static final BlockType WALL_SIGN = register("minecraft:wall_sign", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("waterlogged"), false));
    public static final BlockType WALL_TORCH = register("minecraft:wall_torch", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType WATER = register("minecraft:water", state -> state.with(state.getBlockType().getProperty("level"), 0));
    public static final BlockType WET_SPONGE = register("minecraft:wet_sponge");
    public static final BlockType WHEAT = register("minecraft:wheat", state -> state.with(state.getBlockType().getProperty("age"), 0));
    public static final BlockType WHITE_BANNER = register("minecraft:white_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType WHITE_BED = register("minecraft:white_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType WHITE_CARPET = register("minecraft:white_carpet");
    public static final BlockType WHITE_CONCRETE = register("minecraft:white_concrete");
    public static final BlockType WHITE_CONCRETE_POWDER = register("minecraft:white_concrete_powder");
    public static final BlockType WHITE_GLAZED_TERRACOTTA = register("minecraft:white_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType WHITE_SHULKER_BOX = register("minecraft:white_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType WHITE_STAINED_GLASS = register("minecraft:white_stained_glass");
    public static final BlockType WHITE_STAINED_GLASS_PANE = register("minecraft:white_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType WHITE_TERRACOTTA = register("minecraft:white_terracotta");
    public static final BlockType WHITE_TULIP = register("minecraft:white_tulip");
    public static final BlockType WHITE_WALL_BANNER = register("minecraft:white_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType WHITE_WOOL = register("minecraft:white_wool");
    public static final BlockType WITHER_SKELETON_SKULL = register("minecraft:wither_skeleton_skull", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType WITHER_SKELETON_WALL_SKULL = register("minecraft:wither_skeleton_wall_skull", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType YELLOW_BANNER = register("minecraft:yellow_banner", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType YELLOW_BED = register("minecraft:yellow_bed", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH).with(state.getBlockType().getProperty("occupied"), false).with(state.getBlockType().getProperty("part"), "foot"));
    public static final BlockType YELLOW_CARPET = register("minecraft:yellow_carpet");
    public static final BlockType YELLOW_CONCRETE = register("minecraft:yellow_concrete");
    public static final BlockType YELLOW_CONCRETE_POWDER = register("minecraft:yellow_concrete_powder");
    public static final BlockType YELLOW_GLAZED_TERRACOTTA = register("minecraft:yellow_glazed_terracotta", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType YELLOW_SHULKER_BOX = register("minecraft:yellow_shulker_box", state -> state.with(state.getBlockType().getProperty("facing"), Direction.UP));
    public static final BlockType YELLOW_STAINED_GLASS = register("minecraft:yellow_stained_glass");
    public static final BlockType YELLOW_STAINED_GLASS_PANE = register("minecraft:yellow_stained_glass_pane", state -> state.with(state.getBlockType().getProperty("east"), false).with(state.getBlockType().getProperty("north"), false).with(state.getBlockType().getProperty("south"), false).with(state.getBlockType().getProperty("waterlogged"), false).with(state.getBlockType().getProperty("west"), false));
    public static final BlockType YELLOW_TERRACOTTA = register("minecraft:yellow_terracotta");
    public static final BlockType YELLOW_WALL_BANNER = register("minecraft:yellow_wall_banner", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));
    public static final BlockType YELLOW_WOOL = register("minecraft:yellow_wool");
    public static final BlockType ZOMBIE_HEAD = register("minecraft:zombie_head", state -> state.with(state.getBlockType().getProperty("rotation"), 0));
    public static final BlockType ZOMBIE_WALL_HEAD = register("minecraft:zombie_wall_head", state -> state.with(state.getBlockType().getProperty("facing"), Direction.NORTH));


    private static BlockType register(String id) {
    	return register(new BlockType(id));
    }

    private static BlockType register(String id, Function<BlockState, BlockState> values) {
    	return register(new BlockType(id, values));
    }

    public static BlockType register(BlockType type) {
    	if(sortedRegistry == null) {
    		sortedRegistry = new ArrayList<>();
    		stateList = new ArrayList<>();
    		$NAMESPACES = new LinkedHashSet<>();
            BIT_OFFSET = MathMan.log2nlz(WorldEdit.getInstance().getPlatformManager().queryCapability(Capability.GAME_HOOKS).getRegistries().getBlockRegistry().registerBlocks().size());
            BIT_MASK = ((1 << BIT_OFFSET) - 1);
    	}
    	if(!sortedRegistry.contains(type))sortedRegistry.add(type);
    	return internalRegister(type, sortedRegistry.indexOf(type));
    }

    private static ArrayList<BlockType> sortedRegistry;
    private static ArrayList<BlockState> stateList;
    public static BlockType[] values;
    public static BlockState[] states;
    private static Set<String> $NAMESPACES;
    @Deprecated public static int BIT_OFFSET; // Used internally
    @Deprecated public static int BIT_MASK; // Used internally

    private static BlockType internalRegister(BlockType blockType, final int internalId) {
        init(blockType, blockType.getId(), internalId, stateList);
        if(BlockType.REGISTRY.get(blockType.getId()) == null) BlockType.REGISTRY.register(blockType.getId(), blockType);
        $NAMESPACES.add(blockType.getNamespace());
        values = sortedRegistry.toArray(new BlockType[sortedRegistry.size()]);
        states = stateList.toArray(new BlockState[stateList.size()]);
        return blockType;
    }

    private static void init(BlockType type, String id, int internalId, ArrayList<BlockState> states) {
        try {
            type.setSettings(new Settings(type, id, internalId, states));
            states.addAll(type.updateStates());
            type.setStates(states);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    /*
     -----------------------------------------------------
                    Settings
     -----------------------------------------------------
     */
    public final static class Settings {
        protected final int internalId;
        protected final ItemType itemType;
        protected BlockState defaultState;
        protected final AbstractProperty<?>[] propertiesMapArr;
        protected final AbstractProperty<?>[] propertiesArr;
        protected final List<AbstractProperty<?>> propertiesList;
        protected final Map<String, AbstractProperty<?>> propertiesMap;
        protected final Set<AbstractProperty<?>> propertiesSet;
        protected final BlockMaterial blockMaterial;
        protected final int permutations;
        protected int[] stateOrdinals;
        protected ArrayList<BlockState> localStates;

        Settings(BlockType type, String id, int internalId, List<BlockState> states) {
            this.internalId = internalId;

            int maxInternalStateId = 0;
            Map<String, ? extends Property<?>> properties = WorldEdit.getInstance().getPlatformManager().queryCapability(Capability.GAME_HOOKS).getRegistries().getBlockRegistry().getProperties(type);
            if (!properties.isEmpty()) {
                // Ensure the properties are registered
                int maxOrdinal = 0;
                for (String key : properties.keySet()) {
                    maxOrdinal = Math.max(PropertyKey.getOrCreate(key).ordinal(), maxOrdinal);
                }
                this.propertiesMapArr = new AbstractProperty[maxOrdinal + 1];
                int prop_arr_i = 0;
                this.propertiesArr = new AbstractProperty[properties.size()];
                HashMap<String, AbstractProperty<?>> propMap = new HashMap<>();

                int bitOffset = 0;
                for (Map.Entry<String, ? extends Property<?>> entry : properties.entrySet()) {
                    PropertyKey key = PropertyKey.getOrCreate(entry.getKey());
                    AbstractProperty<?> property = ((AbstractProperty<?>) entry.getValue()).withOffset(bitOffset);
                    this.propertiesMapArr[key.ordinal()] = property;
                    this.propertiesArr[prop_arr_i++] = property;
                    propMap.put(entry.getKey(), property);
                    bitOffset += property.getNumBits();

                    maxInternalStateId += (property.getValues().size() << bitOffset);
                }
                this.propertiesList = Arrays.asList(this.propertiesArr);
                this.propertiesMap = Collections.unmodifiableMap(propMap);
                this.propertiesSet = new LinkedHashSet<>(this.propertiesMap.values());
            } else {
                this.propertiesMapArr = new AbstractProperty[0];
                this.propertiesArr = this.propertiesMapArr;
                this.propertiesList = Collections.emptyList();
                this.propertiesMap = Collections.emptyMap();
                this.propertiesSet = Collections.emptySet();
            }
            this.permutations = maxInternalStateId;
            this.localStates = new ArrayList<>();

            this.blockMaterial = WorldEdit.getInstance().getPlatformManager().queryCapability(Capability.GAME_HOOKS).getRegistries().getBlockRegistry().getMaterial(type);
            this.itemType = ItemTypes.get(type);
            
            if (!propertiesList.isEmpty()) {
                this.stateOrdinals = generateStateOrdinals(internalId, states.size(), maxInternalStateId, propertiesList);
                for (int propId = 0; propId < this.stateOrdinals.length; propId++) {
                    int ordinal = this.stateOrdinals[propId];
                    if (ordinal != -1) {
                        int stateId = internalId + (propId << BlockTypes.BIT_OFFSET);
                        this.localStates.add(new BlockStateImpl(type, stateId, ordinal));
                    }
                }
                
              this.defaultState = this.localStates.get(this.stateOrdinals[internalId >> BlockTypes.BIT_OFFSET] - states.size());     
            } else {
                this.defaultState = new BlockStateImpl(id.contains("minecraft:__reserved__") ? new BlockType("minecraft:air") : type, internalId, states.size());
                this.localStates.add(this.defaultState);
            }
        }
    }
    
    private static int[] generateStateOrdinals(int internalId, int ordinal, int maxStateId, List<AbstractProperty<?>> props) {
        if (props.isEmpty()) return null;
        int[] result = new int[maxStateId + 1];
        Arrays.fill(result, -1);
        int[] state = new int[props.size()];
        int[] sizes = new int[props.size()];
        for (int i = 0; i < props.size(); i++) {
            sizes[i] = props.get(i).getValues().size();
        }
        int index = 0;
        outer:
        while (true) {
            // Create the state
            int stateId = internalId;
            for (int i = 0; i < state.length; i++) {
                stateId = props.get(i).modifyIndex(stateId, state[i]);
            }
            // Map it to the ordinal
            result[stateId >> BlockTypes.BIT_OFFSET] = ordinal++;
            // Increment the state
            while (++state[index] == sizes[index]) {
                state[index] = 0;
                index++;
                if (index == state.length) break outer;
            }
            index = 0;
        }
        return result;
    }

    public static BlockType parse(final String type) throws InputParseException {
        final String inputLower = type.toLowerCase();
        String input = inputLower;

        if (!input.split("\\[", 2)[0].contains(":")) input = "minecraft:" + input;
        BlockType result = BlockType.REGISTRY.get(input);
        if (result != null) return result;

        try {
            BlockStateHolder block = LegacyMapper.getInstance().getBlockFromLegacy(input);
            if (block != null) return block.getBlockType();
        } catch (NumberFormatException e) {
        } catch (IndexOutOfBoundsException e) {}

        throw new SuggestInputParseException("Does not match a valid block type: " + inputLower, inputLower, () -> Stream.of(BlockTypes.values)
            .filter(b -> b.getId().contains(inputLower))
            .map(e1 -> e1.getId())
            .collect(Collectors.toList())
        );
    }

    public static Set<String> getNameSpaces() {
        return $NAMESPACES;
    }

	public static final @Nullable BlockType get(final String id) {
	  return BlockType.REGISTRY.get(id.toLowerCase());
	}

	public static final @Nullable BlockType get(final CharSequence id) {
	  return BlockType.REGISTRY.get(id.toString().toLowerCase());
	}

    @Deprecated
    public static final BlockType get(final int ordinal) {
        return values[ordinal];
    }

    @Deprecated
    public static final BlockType getFromStateId(final int internalStateId) {
        return values[internalStateId & BIT_MASK];
    }

    @Deprecated
    public static final BlockType getFromStateOrdinal(final int internalStateOrdinal) {
        return states[internalStateOrdinal].getBlockType();
    }

    public static int size() {
        return values.length;
    }
    
}

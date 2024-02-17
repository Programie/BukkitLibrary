package com.selfcoders.bukkitlibrary;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.WallSign;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class SignUtils {
    final static List<BlockFace> BLOCK_FACES = Arrays.asList(BlockFace.UP, BlockFace.EAST, BlockFace.NORTH, BlockFace.WEST, BlockFace.SOUTH);

    public static boolean hasBlockSign(List<Block> blocks) {
        return hasBlockSign(blocks, null);
    }

    public static boolean hasBlockSign(List<Block> blocks, Predicate<Sign> predicate) {
        for (Block block : blocks) {
            if (hasBlockSign(block, predicate)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check whether the block has a sign attached to it or is a sign itself.
     *
     * @param block The block to check
     * @return true if a sign was found, false otherwise
     */
    public static boolean hasBlockSign(Block block) {
        return hasBlockSign(block, null);
    }

    /**
     * Check whether the block has a sign attached to it or is a sign itself.
     *
     * @param block     The block to check
     * @param predicate Optional predicate to check the sign (i.e. to check sign content)
     * @return true if a sign was found matching the optional predicate, false otherwise
     */
    public static boolean hasBlockSign(Block block, Predicate<Sign> predicate) {
        // Check whether this block is a sign
        if (Tag.ALL_SIGNS.isTagged(block.getType())) {
            if (predicate == null) {
                return true;
            }

            if (predicate.test((Sign) block.getState())) {
                return true;
            }
        }

        // Check each block face for a sign
        for (BlockFace blockFace : BLOCK_FACES) {
            if (checkSignAttachedToBlockFace(block, blockFace, predicate)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check whether the specified block face has a sign attached to it.
     *
     * @param block     The block to check
     * @param blockFace The block face of the block to check
     * @return true if a sign was found, false otherwise
     */
    public static boolean checkSignAttachedToBlockFace(Block block, BlockFace blockFace) {
        return checkSignAttachedToBlockFace(block, blockFace, null);
    }

    /**
     * Check whether the specified block face has a sign attached to it.
     *
     * @param block     The block to check
     * @param blockFace The block face of the block to check
     * @param predicate Optional predicate to check the sign (i.e. to check sign content)
     * @return true if a sign was found matching the optional predicate, false otherwise
     */
    public static boolean checkSignAttachedToBlockFace(Block block, BlockFace blockFace, Predicate<Sign> predicate) {
        Block faceBlock = block.getRelative(blockFace);
        Material faceBlockType = faceBlock.getType();

        // Check block face for wall sign
        if (Tag.WALL_SIGNS.isTagged(faceBlockType)) {
            Sign signBlock = (Sign) faceBlock.getState();
            BlockFace attachedFace = ((WallSign) signBlock.getBlockData()).getFacing();
            if (blockFace.equals(attachedFace) && (predicate == null || predicate.test(signBlock))) {
                return true;
            }
        }

        // If block face is UP, check for standing sign
        if (blockFace.equals(BlockFace.UP) && Tag.STANDING_SIGNS.isTagged(faceBlockType)) {
            if (predicate == null) {
                return true;
            }

            if (predicate.test((Sign) faceBlock.getState())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get the sign from the specified block.
     *
     * @param block The block to use
     * @return The sign instance, null otherwise
     */
    public static Sign getSignFromBlock(Block block) {
        // TODO: Use Tag.ALL_SIGNS.isTagged(block.getType())
        BlockData blockData = block.getBlockData();

        if (!(blockData instanceof WallSign) && !(blockData instanceof org.bukkit.block.data.type.Sign)) {
            return null;
        }

        BlockState blockState = block.getState();

        if (!(blockState instanceof Sign)) {
            return null;
        }

        return (Sign) blockState;
    }
}
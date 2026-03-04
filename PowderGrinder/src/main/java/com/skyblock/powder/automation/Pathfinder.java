package com.skyblock.powder.automation;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraft.client.player.LocalPlayer;

import java.util.*;

public class Pathfinder {
    
    private final Queue<BlockPos> currentPath = new LinkedList<>();
    private BlockPos targetPos = null;
    private long lastPathfindTime = 0;
    private static final long PATHFIND_COOLDOWN = 1000; // 1 second
    
    private static final int MAX_PATH_LENGTH = 256;
    private static final int SEARCH_RADIUS = 64;
    
    public void update() {
        // Path is calculated on-demand when needed
    }
    
    public List<BlockPos> findPathToTarget(LocalPlayer player, BlockPos target, Level level) {
        if (System.currentTimeMillis() - lastPathfindTime < PATHFIND_COOLDOWN) {
            return new ArrayList<>(currentPath);
        }
        
        lastPathfindTime = System.currentTimeMillis();
        BlockPos startPos = player.blockPosition();
        
        List<BlockPos> path = aStarSearch(startPos, target, level);
        currentPath.clear();
        currentPath.addAll(path);
        
        return path;
    }
    
    private List<BlockPos> aStarSearch(BlockPos start, BlockPos goal, Level level) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<BlockPos> closedSet = new HashSet<>();
        Map<BlockPos, BlockPos> cameFrom = new HashMap<>();
        Map<BlockPos, Double> gScore = new HashMap<>();
        
        Node startNode = new Node(start, 0, heuristic(start, goal));
        openSet.add(startNode);
        gScore.put(start, 0.0);
        
        int iterations = 0;
        final int MAX_ITERATIONS = 5000;
        
        while (!openSet.isEmpty() && iterations++ < MAX_ITERATIONS) {
            Node current = openSet.poll();
            BlockPos currentPos = current.pos;
            
            if (currentPos.equals(goal)) {
                return reconstructPath(cameFrom, currentPos);
            }
            
            closedSet.add(currentPos);
            
            // Get neighbors (up, down, forward, backward, left, right)
            for (BlockPos neighbor : getWalkableNeighbors(currentPos, level)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }
                
                double tentativeGScore = gScore.getOrDefault(currentPos, Double.MAX_VALUE) + 1.0;
                
                if (!gScore.containsKey(neighbor) || tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    cameFrom.put(neighbor, currentPos);
                    gScore.put(neighbor, tentativeGScore);
                    double fScore = tentativeGScore + heuristic(neighbor, goal);
                    openSet.add(new Node(neighbor, tentativeGScore, fScore));
                }
            }
        }
        
        return new ArrayList<>(); // No path found
    }
    
    private List<BlockPos> getWalkableNeighbors(BlockPos pos, Level level) {
        List<BlockPos> neighbors = new ArrayList<>();
        
        // Check horizontal neighbors
        BlockPos[] horizontalOffsets = {
            pos.north(), pos.south(), pos.east(), pos.west()
        };
        
        for (BlockPos neighbor : horizontalOffsets) {
            if (isWalkable(neighbor, level)) {
                neighbors.add(neighbor);
            }
        }
        
        // Check diagonal neighbors (with height adjustment)
        BlockPos[] diagonals = {
            pos.north().east(), pos.north().west(),
            pos.south().east(), pos.south().west()
        };
        
        for (BlockPos diagonal : diagonals) {
            if (isWalkable(diagonal, level)) {
                neighbors.add(diagonal);
            }
        }
        
        return neighbors;
    }
    
    private boolean isWalkable(BlockPos pos, Level level) {
        // Check if block is air or water
        if (!level.getBlockState(pos).getMaterial().isReplaceable()) {
            return false;
        }
        
        // Check if block below is solid
        BlockPos below = pos.below();
        if (!level.getBlockState(below).getMaterial().isSolid()) {
            return false;
        }
        
        // Avoid lava
        if (level.getBlockState(pos).getBlock() == Blocks.LAVA) {
            return false;
        }
        
        return true;
    }
    
    private double heuristic(BlockPos a, BlockPos b) {
        int dx = Math.abs(a.getX() - b.getX());
        int dy = Math.abs(a.getY() - b.getY());
        int dz = Math.abs(a.getZ() - b.getZ());
        
        // Use Manhattan distance
        return dx + dy + dz;
    }
    
    private List<BlockPos> reconstructPath(Map<BlockPos, BlockPos> cameFrom, BlockPos current) {
        List<BlockPos> path = new ArrayList<>();
        path.add(current);
        
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(0, current);
        }
        
        return path;
    }
    
    private static class Node implements Comparable<Node> {
        BlockPos pos;
        double gScore;
        double fScore;
        
        Node(BlockPos pos, double gScore, double fScore) {
            this.pos = pos;
            this.gScore = gScore;
            this.fScore = fScore;
        }
        
        @Override
        public int compareTo(Node other) {
            return Double.compare(this.fScore, other.fScore);
        }
    }
    
    public void clearPath() {
        currentPath.clear();
        targetPos = null;
    }
}

package edu.weber.cs3230.ObjectOrientedDesign.Interfaces.homework;

/**
 * This class represents a tree with a type and height.
 */
public class Tree implements Measurable {
    private double height;
    private String type;

    /**
     * Constructs a new Tree with the specified type and height.
     *
     * @param type   the type of the tree (e.g., "Willow")
     * @param height the height of the tree
     */
    public Tree(String type, double height) {
        this.type = type;
        this.height = height;
    }

    /**
     * Gets the height of the tree.
     *
     * @return the height of the tree
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the tree.
     *
     * @param height the new height of the tree
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Gets the type of the tree.
     *
     * @return the type of the tree
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the tree.
     *
     * @param type the new type of the tree
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the measure of the tree, which is its height.
     *
     * @return the height of the tree
     */
    @Override
    public double getMeasure() {
        return height;
    }
}

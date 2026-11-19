// File: Enemy.java
public class Enemy {
    // Properties are provided for you
    private String name;
    private int health;
    private int power;
    private int defense;

    // Constructor is provided for you
    public Enemy(String name, int health, int power, int defense) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.defense = defense;
    }

    public String getName() { return this.name; }
    
    // TODO 1: Create the remaining getters and setters for health, power, and defense here
    public int getHealth() { return this.health; }
    public int getPower() { return this.power; }
    public int getDefense() { return this.defense; }

    public void setHealth(int health) { this.health = health; }
    public void setPower(int power) { this.power = power; }
    public void setDefense(int defense) { this.defense = defense; }

    public void attack(Player player) {
        System.out.println(this.name + " attacks " + player.getName() + "!");
        // TODO 2: Call the takeDamage() method on the 'player' object, passing this class's 'power' as the parameter
        player.takeDamage(this.power);
    }

    public void takeDamage(int incomingDamage) {
        // TODO 3: Calculate damageTaken as (incomingDamage - this.defense). 
        int damageTaken = Math.max(0, incomingDamage - this.defense);
        
        // TODO 4: Subtract the calculated damageTaken from this.health
        this.health -= damageTaken;

        // TODO 5: If this.health is lower than or equal to zero, print "{this.name} died!"
        if (this.health <= 0) {
            System.out.println(this.name + " died!");
        }
    }
}
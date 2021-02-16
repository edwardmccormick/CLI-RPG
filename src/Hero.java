import java.math.RoundingMode;
import java.util.Random;

import static java.lang.StrictMath.round;

public class Hero {

    public Hero (String name) {
        this.name = name;
    }

    public String name;
    public double hitpoints = 50;
    public double hitpointsmax = 50;
    public double energy = 5;
    public double maxEnergy = 5;
    public String weapon = "dagger";
    public boolean isStaggered = false;
    public boolean isPrepared = false;
    public boolean isExhausted = false;
    public int maxNormalDamage = 11;


    public double standardAttack() {
        if (energy <= 0) {
            System.out.println("You're exhausted and cannot attack this round!");this.energy = maxEnergy;
        }
        Random attack = new Random();
        this.energy--;
        return attack.nextInt(maxNormalDamage);
    }

    public double recklessAttack() {
        if (energy <= 0) {
            System.out.println("You're exhausted and cannot attack this round!");this.energy = maxEnergy;
        }
        Random attack = new Random();
        Random coinflip = new Random();
        energy -= 2;
        System.out.println("You throw caution to the wind, charging towards your opponent recklessly swinging your " + this.weapon + " in an effort to do additional damage!");
        if (coinflip.nextInt(2) == 0) {
            this.isStaggered = true;
            System.out.println("Your recklessness costs you dearly though - you do more damage but throw yourself off balance!");}
        else {
            System.out.println("Amazingly, despite your wild attack, you manage to land with your feet underneath you and suffer no ill effects!");
        }
        return attack.nextInt(maxNormalDamage*2);
    }

    public double carefulAttack() {
        if (energy <= 0) {
            System.out.println("You're exhausted and cannot attack this round!");this.energy = maxEnergy;
        }
        Random attack = new Random();
        energy++;
        this.isPrepared = true;
        System.out.println("You carefully and strategically advance, trading damage for defensive prowess and conserving your energy.");
        return attack.nextInt( round(maxNormalDamage/2));
    }

    public double damageModifier(double damage) {
        if (energy<=0) {isExhausted = true;}
        if (isExhausted & isStaggered) {reset(); return damage *5;}
        else if (isExhausted) {reset(); return damage * 3;}
        else if (isStaggered) {reset(); return damage * 2;}
        else if (isPrepared) {reset(); return round(damage/2);}
        else {reset(); return damage;}
    }

    public void reset() {
        isPrepared = false;
        isStaggered = false;
        isExhausted = false;
    }

    public String narrateAttack(int damage) {
        if (damage > 50) {return "You careful place your blade, eviscerating your opponent and spilling their bowels and their bowls onto the ground. A MIGHTY strike. \n" + this.name + " did " + damage+ " damage!";}
        else if (damage > 40) {return "You aim a might slash at them, a blow that would have killed lesser men or creatures. You have done them GRAVE damage! \n" + this.name + " did " + damage+ " damage!";}
        else if (damage > 20) {return "You take a mighty swing at your opponent as they step towards you - it's a blow that would have mortally wounded lesser men. Blood sprays from the gaping wound. You've inflicted terrible damage to them. \n" + this.name + " did " + damage + " damage!";}
        else if (damage > 10) {return "You harness your momentum to strike a mighty cleave, staggering your opponent. What a might blow you've struck them! \n" + this.name + " did " + damage+  "damage!";}
        else if (damage > 5) {return "You try to swing your " + this.weapon + " towards your opponent, but the quarters are too tight and your footwork is too uncertain. Still, your blade catches them, leaving a trickle of blood down their side. \n" + this.name + " did " + damage+ " damage!";}
        else if (damage >= 1) {return "You fumble forward awkwardly, your blade unsteadily seeking your opponent. It is not the stuff of heroic epics, but you are quite sure you've wounded them. \n" + this.name + " did " + damage+ " damage!";}
        else if (damage <= 0) {return "You swing your " + this.weapon + " at your opponent, but they dance beyond your reach, mocking you! You missed! \n" + this.name + " did " + damage+ " damage.";}
        else {return "Somehow, you've done something that's broken the fictional world in which you inhabit. It's almost definitely Ted's fault though, so don't be too upset. \n" + this.name + " did " + damage+ " damage!";}
    }

    public double changeHP(double change) {
        this.hitpoints += change;
        if (this.hitpoints > this.hitpointsmax) {this.hitpoints = this.hitpointsmax;}
        return this.hitpoints;
    }

    public String checkStatus() {
        return this.name + " has " + (int)this.hitpoints + " hitpoints left of " + (int)this.hitpointsmax + " hitpoints maximum, and has " + (int)this.energy + " energy left.";
    }
 }

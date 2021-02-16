import java.util.Random;

import static java.lang.StrictMath.round;

public class Enemy {
    public String name = "The Dirty Kobold Hrawak";
    public double hitpoints = 20;
    public double hitpointsmax = 20;
    public double energy = 2;
    public double maxEnergy = 2;
    public boolean isExhausted = false;

    String weapon = "his dirty claws";

    public double standardAttack() {
        if (energy <= 0) {
            System.out.println("You're exhausted and cannot attack this round!");this.energy = this.maxEnergy;
        }
        Random attack = new Random();
        this.energy--;
        return attack.nextInt(5);
    }

       public String narrateAttack(int damage) {
        if (damage > 50) {return this.name + " carefully places their blade, eviscerating you and spilling your bowels and your bowls onto the ground. Oh no! \n" + this.name + " did " + damage+ " damage!";}
        else if (damage > 40) {return this.name + " aims a might slash at you; a blow that would have killed lesser men! Oh no!";}
        else if (damage > 20) {return this.name + " takes a mighty swing at you as you step towards them - it's a blow that would have mortally wounded lesser men. Blood sprays from the gaping wound in your side. \n" + this.name + " did " + damage+ " damage!";}
        else if (damage > 10) {return this.name + " uses " + this.weapon + " to strike a heavy, cleaving blow against you. You feel the warmth of blood gushing from the wound. \n" + this.name + " did " + damage+ " damage!";}
        else if (damage > 5) {return this.name + " tries to swing " + this.weapon + " towards you, but your armor and footwork deflect much of the blow. Still, you feel a trickle of blood down your side. \n" + this.name + " did " + damage+ " damage!";}
        else if (damage >= 1) {return this.name + " fumbles forward lurchingly, flailing " + this.weapon + " at you. It grazes your armor and you feel the sharp heat of a wound open up. \n" + this.name + " did " + damage+ " damage!";}
        else if (damage <= 0) {return this.name + "swings " + this.weapon + " at you, but you dance beyond their reach! And not a scratch on you to boot! \n" + this.name + " did " + damage+ " damage!";}
        else {return "Somehow, you've done something that's broken the fictional world in which you inhabit. It's almost definitely Ted's fault though, so don't be too upset. \n" + this.name + " did " + damage+ " damage!";}
    }

    public double damageModifier(double damage) {
        if (energy<=0) {isExhausted = true;}
//        if (isExhausted & isStaggered) {reset(); return damage *5;}
        if (isExhausted) {reset(); return damage * 3;}
//        else if (isStaggered) {reset(); return damage * 2;}
//        else if (isPrepared) {reset(); return round(damage/2);}
        else {reset(); return damage;}
    }

    public void reset() {
//        isPrepared = false;
//        isStaggered = false;
        this.isExhausted = false;
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

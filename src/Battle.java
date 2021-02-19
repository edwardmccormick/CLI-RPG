import java.util.Scanner;

public class Battle {

    public static boolean battleLoop(Enemy enemy, Hero main) {
        Scanner in = new Scanner(System.in);
        System.out.println("You go first, " + main.name + "! \n 1) Regular attack (uses energy but does fair damage) \n 2) Reckless attack (uses more energy, chance to stumble you, does extra damage) \n 3) Careful attack (conserves energy, helps you take less damage");
        int input = in.nextInt();
        while (true) {
            double heroDamage;
            if (input == 1) {
                heroDamage = main.standardAttack();
            } else if (input == 2) {
                heroDamage = main.recklessAttack();
            } else if (input == 3) {
                heroDamage = main.carefulAttack();
            } else {
                while (true) {
                    System.out.println("I'm sorry, your choice wasn't understood. Your choices are:\n 1) Regular attack (uses energy but does fair damage) \n 2) Reckless attack (uses more energy, chance to stumble you, does extra damage) \n 3) Careful attack (conserves energy, helps you take less damage");
                    input = in.nextInt();
                    if (input == 1) {
                        heroDamage = main.standardAttack();
                        break;
                    } else if (input == 2) {
                        heroDamage = main.recklessAttack();
                        break;
                    } else if (input == 3) {
                        heroDamage = main.carefulAttack();
                        break;
                    } else {
                        continue;
                    }
                }
            }

            enemy.changeHP(enemy.damageModifier(-heroDamage));
            System.out.println(main.narrateAttack((int) enemy.damageModifier(heroDamage)));
            System.out.println(main.checkStatus());
            System.out.println(enemy.checkStatus());
            if (enemy.hitpoints <= 0) {
                System.out.println(enemy.name + " stumbles, takes one last halting breath, lets out a might roar, and then drops to the ground, dead!");
                return true;
//                break;
            }

            System.out.println("Very well, " + main.name + ", but now it's time for " + enemy.name + " to have a turn! Press enter to continue!");
            in.nextLine();
            double enemyDamage = enemy.standardAttack();
            main.changeHP(main.damageModifier(-enemyDamage));
            System.out.println(enemy.narrateAttack((int) main.damageModifier(enemyDamage)));
            System.out.println(main.checkStatus());
            System.out.println(enemy.checkStatus());
            if (main.hitpoints <= 0) {
                System.out.println("Oh no " + main.name + "! " + enemy.name + " cackles as your vision fades and you fall to the ground, defeated!");
                return false;
//                break;
            }
            System.out.println("It's your turn again, " + main.name + ". You can use the following techniques: \n 1) Regular attack (uses energy but does fair damage) \n 2) Reckless attack (uses more energy, chance to stumble you, does extra damage) \n 3) Careful attack (conserves energy, helps you take less damage");
            input = in.nextInt();
        }
//        if (enemy.hitpoints<=0) {return true;} else if (main.hitpoints<=0) {return false;} else {
//            System.out.println("Something very strange has happened");
//            return false;}
    }


    public static void main(String[] args) {
        Enemy enemy = new Enemy();
        Scanner in = new Scanner(System.in);
        System.out.println("Greetings adventurer, by what name should I refer to you?");
        Hero main = new Hero(in.nextLine().trim());
        System.out.println("Hail and well met, " + main.name + "! Let us move with most haste to your adventure!");
        System.out.println("The gate crashes down, and you're locked in the arena with " + enemy.name + ". The only thing you can do is FIGHT TO THE DEATH! <airhorns> Press enter to start combat!");
        in.nextLine();
//        System.out.println("You go first, " + main.name + "! You don't know any moves besides attacking with your trusty " + main.weapon + ", so hit enter to do that!");
        boolean battleResult = battleLoop(enemy, main);
        if (battleResult == true) {
            System.out.println("A booming voice announces over the gladiator pit, \"Congratulations " + main.name + ", you have solved my combat puzzle!\" and the gates open.\nThere is a sword");
        } else if (battleResult == false) {
            System.out.println("A booming voice laughs uproariously as your vision fades, your life blood spilling out of you and staining the sand spread over the arena floor. \"Goodbye " + main.name + ", and perhaps better luck in the next life! MUAHAHAHAHA\" \n \nTHE END...OR IS IT?");
        }
    }
}
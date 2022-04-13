package knz.mathknigt.restApi.game;

import static knz.mathknigt.configs.ConfigConstants.MAXIMALMENTALIMPACTVALUE;
import static knz.mathknigt.configs.ConfigConstants.MAXIMALPHYSICALIMPACTVALUE;

public class KnightHadler {
    public static void attackP(Knight attacker, Knight defender, boolean asucsessful, boolean dsucsessful){
        if (!asucsessful)
            return;
        if (dsucsessful)
            takeDamage(defender, reducePhysicalDamage(attacker.getPhysicalImpact(), defender.getPhysicalImpact()));
        else
            takeDamage(defender, attacker.getPhysicalImpact());
    }

    public static void attackM(Knight attacker, Knight defender, boolean asucsessful, boolean dsucsessful){
        if (!asucsessful)
            return;
        if (dsucsessful)
            takeDamage(defender, reduceMentalDamage(attacker.getMentalImpact(), defender.getMentalImpact()));
        else
            takeDamage(defender, attacker.getMentalImpact());
    }

    public static Long reduceMentalDamage(Long damage, Long reducerValue){
        return damage*(MAXIMALMENTALIMPACTVALUE - reducerValue)/MAXIMALMENTALIMPACTVALUE;
    }
    public static Long reducePhysicalDamage(Long damage, Long reducerValue){
        return damage*(MAXIMALPHYSICALIMPACTVALUE - reducerValue)/MAXIMALPHYSICALIMPACTVALUE;
    }
    public static void takeDamage(Knight defender, Long damage){
        defender.setStamina(defender.getStamina()-damage);
    }

    public static boolean isWeak(Knight knight){
        return knight.getStamina() < 1;
    }
}

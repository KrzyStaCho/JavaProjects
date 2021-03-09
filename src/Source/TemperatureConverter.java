package Source;

import java.lang.annotation.Target;

public class TemperatureConverter {
    public static void run(String args) {
        TemperatureConverter.main(args.split(" "));
    }

    public static void main(String[] args) {

        if(args.length!=3) {
            System.out.println("Nieprawidłowa ilość argumentów");
            return;
        }

        try {
            float unitToConvert = Float.valueOf(args[0]);

            Units unitFrom = GetUnitsFromString(args[1]);
            Units unitTo = GetUnitsFromString(args[2]);

            if(unitFrom==null) {
                System.out.println("Niewłaściwa jednostka 1");
                return;
            }

            if(unitTo==null) {
                System.out.println("Niewłaściwa jednostka 2");
                return;
            }

            System.out.println(ConvertTo(unitFrom, unitToConvert, unitTo));

        } catch(NumberFormatException e) {
            System.out.println("Nieprawidłowa wartość zmiennej");
            return;
        }
    }

    public static Units GetUnitsFromString(String unit) {
        if(unit.equals(Units.Fahrenheit.toString())) return Units.Fahrenheit;
        else if(unit.equals(Units.Rankine.toString())) return  Units.Rankine;
        else if(unit.equals(Units.Kelvin.toString())) return Units.Kelvin;
        else if(unit.equals(Units.Celsius.toString())) return Units.Celsius;
        else return null;
    }

    public static float ConvertTo(Units from, float value, Units to) {
        value = UnitConverter.ToCelsius(from, value);
        value = UnitConverter.FromCelsius(to, value);
        return value;
    }
}

class UnitConverter {

    public static float FahrenheitToCelsius(float f) {
        return (f - 32) * 5/9;
    }

    public static float RankineToCelsius(float r) {
        return (r - 491.67f) * 5/9;
    }

    public static float KelvinToCelsius(float k) {
        return k - 273.15f;
    }

    public static float CelsiusToFahrenheit(float c) {
        return c * 9/5 + 32;
    }

    public static float CelsiusToRankine(float c) {
        return (c + 273.15f) * 9/5;
    }

    public static float CelsiusToKelvin(float c) {
        return c + 273.15f;
    }

    public static float ToCelsius(Units from, float value) {
        switch(from) {
            case Fahrenheit:
                return FahrenheitToCelsius(value);
            case Rankine:
                return RankineToCelsius(value);
            case Kelvin:
                return KelvinToCelsius(value);
            default:
                return value;
        }
    }

    public static float FromCelsius(Units to, float value) {
        switch(to) {
            case Fahrenheit:
                return CelsiusToFahrenheit(value);
            case Rankine:
                return CelsiusToRankine(value);
            case Kelvin:
                return CelsiusToKelvin(value);
            default:
                return value;
        }
    }
}

enum Units {
    Fahrenheit("F"),
    Rankine("R"),
    Celsius("C"),
    Kelvin("K");

    String shortName;

    Units(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return this.shortName;
    }
}

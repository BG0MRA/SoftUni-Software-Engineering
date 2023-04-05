package football.core;


import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.LinkedHashMap;
import java.util.Map;

import static football.common.ConstantMessages.*;
import static football.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private SupplementRepository supplement;
    private Map<String, Field> fields;

    public ControllerImpl() {
        this.supplement = new SupplementRepositoryImpl();
        this.fields = new LinkedHashMap<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        if (fieldType.equals("ArtificialTurf")) {
            Field field = new ArtificialTurf(fieldName);
            fields.put(fieldName, field);

        } else if (fieldType.equals("NaturalGrass")) {
            Field field = new NaturalGrass(fieldName);
            fields.put(fieldName, field);

        } else {
            throw new NullPointerException(INVALID_FIELD_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        if (type.equals("Powdered")) {
            Powdered powdered = new Powdered();
            supplement.add(powdered);

        } else if (type.equals("Liquid")) {
            Liquid liquid = new Liquid();
            supplement.add(liquid);

        } else {
            throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        if (!(supplementType.equals("Powdered") || supplementType.equals("Liquid"))) {
            throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }
        Field currentField = fields.get(fieldName);
        Supplement currentSupplement = supplement.findByType(supplementType);
        if (currentSupplement == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }
        currentField.addSupplement(currentSupplement);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Field givenField = fields.get(fieldName);

        if (playerType.equals("Men")) {
            Men player = new Men(playerName, nationality, strength);
            if (givenField.getClass().getSimpleName().equals("ArtificialTurf")) {
                return String.format(FIELD_NOT_SUITABLE);
            }
            givenField.addPlayer(player);

        } else if (playerType.equals("Women")) {
            Women player = new Women(playerName, nationality, strength);
            if (givenField.getClass().getSimpleName().equals("NaturalGrass")) {
                return String.format(FIELD_NOT_SUITABLE);
            }
            givenField.addPlayer(player);
        } else {
            throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerName, fieldName);
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field givenField = fields.get(fieldName);

        givenField.drag();
        int draggedPlayers = givenField.getPlayers().size();

        return String.format(PLAYER_DRAG, draggedPlayers);
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field givenField = fields.get(fieldName);
        int result = givenField.getPlayers()
                .stream()
                .mapToInt(player -> player.getStrength())
                .sum();

//        int result = 0;
//        for (Player player : givenField.getPlayers()) {
//            result += player.getStrength();
//        }

        return String.format(STRENGTH_FIELD, fieldName, result);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Field field : fields.values()) {
             sb.append(field.getInfo());
            //sb.append(System.lineSeparator());

        }
        return sb.toString().trim();
    }
}

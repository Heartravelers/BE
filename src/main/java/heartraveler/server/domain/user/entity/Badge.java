package heartraveler.server.domain.user.entity;

public enum Badge {
    CITY_EXPLORATION, PRACTICAL, TRAVEL_HOLIC,
    ACTIVITY, FLEX, HEALING_TRAVEL, ROMANTIC_P,
    EMOTIONAL, THRIFTY_TRAVEL, BREAD_PILGRIMAGE,
    VIEW_SPOT, PLANNING_J, NATURE_EXPLORATION;

    public static Badge fromString(String name) {
        if(name == null) {
            return null;
        } else {
            return valueOf(name.toUpperCase());
        }
    }
}

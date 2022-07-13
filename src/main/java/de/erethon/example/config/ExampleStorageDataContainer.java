package de.erethon.example.config;

import de.erethon.bedrock.chat.MessageUtil;
import de.erethon.bedrock.config.storage.Nullability;
import de.erethon.bedrock.config.storage.StorageData;
import de.erethon.bedrock.config.storage.StorageDataContainer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Fyreum
 */
public class ExampleStorageDataContainer extends StorageDataContainer {

    public static final int CONFIG_VERSION = 1;

    /*
     No explicit behaviour defined -> default behaviour apply.
    */
    @StorageData
    private String string = "Simple String";

    /*
     log = true states that the process of initializing, loading & saving should be logged.
     nullability = Nullability.IGNORE // States that null values shouldn't be loaded or saved.
    */
    @StorageData(log = true, nullability = Nullability.IGNORE)
    private int integer = 10;

    /*
      debug = true // States that the process of initializing, loading & saving should be debugged.

      Debugging includes more details about the actual values than logging and is more explicit about
      the ongoing process.
    */
    @StorageData(debug = true)
    private String debuggedString = "String text";

    /*
     type = ArraysList.class // States the implementing list class type, as the field itself doesn't.
     valueTypes = String.class // States the class type contained by the list.
    */
    @StorageData(type = ArrayList.class, valueTypes = String.class)
    private List<String> stringList = new ArrayList<>();

    /*
     type = HashMap.class // Same as for the list, we have to state the type manually here.
     keyTypes = String.class // States the key class type contained by the map.
     valueTypes = Integer.class // States the value class contained by the map.
    */
    @StorageData(type = HashMap.class, keyTypes = String.class, valueTypes = Integer.class)
    private Map<String, Integer> stringIntegerMap = new HashMap<>();

    /*
     type = HashMap.class is not needed here, as the field type is already a HashMap.
     keyTypes = {String.class, Integer.class} // States that the first map key type is a String and second map's an Integer.
     valueTypes = {Map.class, Boolean.class} // States that the first map value type is Map and the second map's a Boolean.

     NOTE: Inner collections are always ArrayLists and inner maps are HashMaps.
    */
    @StorageData(keyTypes = {String.class, Integer.class}, valueTypes = {Map.class, Boolean.class})
    private HashMap<String, Map<Integer, Boolean>> stringIntegerBooleanMap = new HashMap<>();

    public ExampleStorageDataContainer(File file) {
        super(file, CONFIG_VERSION);
        defaultLoadProcess();
        if (initialize) { // only put values if the was empty or non-existent before.
            putSomeValues();
        }
        MessageUtil.log(string);
        MessageUtil.log(String.valueOf(integer));
        MessageUtil.log(debuggedString);
        MessageUtil.log(stringList.toString());
        MessageUtil.log(stringIntegerMap.toString());
        MessageUtil.log(stringIntegerBooleanMap.toString());
    }

    public void putSomeValues() {
        stringList.addAll(List.of("String 1", "String 2", "String 3"));
        stringIntegerBooleanMap.put("String key", Map.of(1, true, 2, false));
    }

    /* getter and setter */

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public String getDebuggedString() {
        return debuggedString;
    }

    public void setDebuggedString(String debuggedString) {
        this.debuggedString = debuggedString;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Map<String, Integer> getStringIntegerMap() {
        return stringIntegerMap;
    }

    public void setStringIntegerMap(Map<String, Integer> stringIntegerMap) {
        this.stringIntegerMap = stringIntegerMap;
    }

    public HashMap<String, Map<Integer, Boolean>> getStringIntegerBooleanMap() {
        return stringIntegerBooleanMap;
    }

    public void setStringIntegerBooleanMap(HashMap<String, Map<Integer, Boolean>> stringIntegerBooleanMap) {
        this.stringIntegerBooleanMap = stringIntegerBooleanMap;
    }
}

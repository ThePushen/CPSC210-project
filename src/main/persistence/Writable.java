package persistence;

import org.json.JSONObject;

// Method and ideas from JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

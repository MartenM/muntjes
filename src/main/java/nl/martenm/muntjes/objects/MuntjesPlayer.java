package nl.martenm.muntjes.objects;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.*;

public class MuntjesPlayer implements ConfigurationSerializable {

    private List<Runnable> onChange = new ArrayList<>();

    private UUID uuid;
    private String name;
    private String nickName;
    private int muntjes;
    private int totalMuntjes;

    public MuntjesPlayer(UUID uuid, String name, String nickName, int muntjes, int totalMuntjes) {
        this.uuid = uuid;
        this.name = name;
        this.muntjes = muntjes;
        this.nickName = nickName == null ? name : nickName;
        this.totalMuntjes = totalMuntjes;
    }

    public MuntjesPlayer(Map<String, Object> data) {
        this.uuid = UUID.fromString((String) data.get("uuid"));
        this.name = (String) data.get("name");
        this.nickName = (String) data.get("nick-name");
        this.muntjes = (Integer) data.get("muntjes");
        this.totalMuntjes = (Integer) data.getOrDefault("total", 0);
    }

    public Map<String, Object> serialize() {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("uuid", uuid.toString());
        data.put("name", name);
        data.put("nick-name", nickName);
        data.put("muntjes", muntjes);
        data.put("total", totalMuntjes);
        return data;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMuntjes() {
        return muntjes;
    }

    public void addMuntjes(int amount) {
        assert(amount >= 0);
        this.muntjes += amount;
        this.totalMuntjes += amount;
        notifyChanged();
    }

    public void takeMuntjes(int amount) {
        assert(amount >= 0);
        this.muntjes -= amount;
        notifyChanged();
    }

    public int getTotalMuntjes() {
        return totalMuntjes;
    }

    private void notifyChanged() {
        this.onChange.forEach(Runnable::run);
    }

    public void addOnChange(Runnable runnable) {
        this.onChange.add(runnable);
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }
}

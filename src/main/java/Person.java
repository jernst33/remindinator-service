import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.Instant;

@MongoEntity(collection="persons")
public class Person extends PanacheMongoEntity {
    private String name;
    private Instant birth;
    private Integer weight;
    private Boolean human;
    private Integer numberOfTeeth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getBirth() {
        return birth;
    }

    public void setBirth(Instant birth) {
        this.birth = birth;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getHuman() {
        return human;
    }

    public void setHuman(Boolean human) {
        this.human = human;
    }

    public Integer getNumberOfTeeth() {
        return numberOfTeeth;
    }

    public void setNumberOfTeeth(Integer numberOfTeeth) {
        this.numberOfTeeth = numberOfTeeth;
    }
}
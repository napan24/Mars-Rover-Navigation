// Use Case: Suppose we want to create a House object with many configurable options.
class House {
    private String foundation;
    private String structure;
    private String roof;
    private String interior;

    public static class Builder {
        private String foundation;
        private String structure;
        private String roof;
        private String interior;

        public Builder buildFoundation(String foundation) {
            this.foundation = foundation;
            return this;
        }

        public Builder buildStructure(String structure) {
            this.structure = structure;
            return this;
        }

        public Builder buildRoof(String roof) {
            this.roof = roof;
            return this;
        }

        public Builder buildInterior(String interior) {
            this.interior = interior;
            return this;
        }

        public House build() {
            House house = new House();
            house.foundation = this.foundation;
            house.structure = this.structure;
            house.roof = this.roof;
            house.interior = this.interior;
            return house;
        }
    }

    @Override
    public String toString() {
        return "House built with: " + "foundation=" + foundation + ", structure=" + structure + ", roof=" + roof + ", interior=" + interior;
    }
}

public class BuilderPatternDemo {
    public static void main(String[] args) {
        House house = new House.Builder()
            .buildFoundation("Concrete")
            .buildStructure("Wood")
            .buildRoof("Tiles")
            .buildInterior("Drywall")
            .build();

        System.out.println(house);
    }
}

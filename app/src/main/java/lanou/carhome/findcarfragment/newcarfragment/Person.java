package lanou.carhome.findcarfragment.newcarfragment;

/**
 * Created by dllo on 16/9/27.
 */

    public class Person {
        private String name;
        private String pinYinName;

        public Person(String name) {
            super();
            this.name = name;
        }

        public Person(String name, String pinYinName) {
            super();
            this.name = name;
            this.pinYinName = pinYinName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPinYinName() {
            return pinYinName;
        }

        public void setPinYinName(String pinYinName) {
            this.pinYinName = pinYinName;
        }

    }



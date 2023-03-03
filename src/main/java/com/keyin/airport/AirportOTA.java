package com.keyin.airport;

// public class AirportOTA is a public class and is mapped to the table airport in the database
// AirportOTA's purpose is to transfer data between the database (backend) and the application (front end)
public class AirportOTA {

        private String name;
        private String code;

        private long cityId;

        private long aircraftId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getCityId() {
            return cityId;
        }

        public void setCityId(long cityId) {
            this.cityId = cityId;
        }

        public long getAircraftId() {
            return aircraftId;
        }

        public void setAircraftId(long aircraftId) {
            this.aircraftId = aircraftId;
        }
    }

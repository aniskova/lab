package by.gsu.epamlab.model.beans;

import java.sql.Date;

public enum Section {
    TODAY("date <= CURDATE() AND state = "+"\'"+ TaskState.ACTIVE + "\'"){
        @Override
        public Date getSectionDate() {
            return new Date(System.currentTimeMillis());
        }
    },
    TOMORROW("date = DATE_ADD(CURDATE(), INTERVAL 1 DAY) AND state = "+"\'"+TaskState.ACTIVE+"\'"){
        @Override
        public Date getSectionDate() {
            final long OFFSET_1_DAY_IN_MILLIS = 86_400_000;
            return new Date(System.currentTimeMillis() + OFFSET_1_DAY_IN_MILLIS);
        }
    },
   // TOMORROW("CURDATE() = DATE(DATE_ADD(date, INTERVAL 1 DAY))AND state = 'ACTIVE'"),
    SOMEDAY("date > DATE_ADD(CURDATE(), INTERVAL 1 DAY)AND state = " +"\'"+ TaskState.ACTIVE+"\'"),
    DELETED("state = "+"\'"+TaskState.DELETED+"\'"),
    FIXED("state = "+"\'"+TaskState.FIXED+"\'");
    String s;
    Section(String s) {
        this.s=s;
    }


    @Override
    public String toString() {
        return s;
    }
    public Date getSectionDate(){
        throw new IllegalArgumentException();
    };
}

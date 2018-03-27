public class Main {

    public static void main(String[] args) {
        Music musicArr[] = new Music[]{
                new Music("song1", 220, true),
                new Music("song2", 140, true),
                new Music("song3", 5, false),
                new Music("song4", 20, true),
                new Music("song5", 10, false),
                new Music("song6", 0, true),
                new Music("song7", 70, false),
                new Music("song8", 14, true),
                new Music("song9", 2240, true),
                new Music("song10", 277, false)};
        int sumAmount = 0;
        int sumLoaded = 0;
        for (Music music : musicArr) {
           if (music.isLoad()==true){
               sumLoaded++;
           }
           sumAmount += music.getAmount();
        }
        System.out.println("Summary amount of sailed copies = " + sumAmount);
        System.out.println("Summary amount of loaded copies = " + sumLoaded);

    }
}

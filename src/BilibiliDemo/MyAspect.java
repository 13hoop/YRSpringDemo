package BilibiliDemo;

public class MyAspect {

    public void beforUsing() {
        System.out.println(" ----- > before < ----- ");
    }

    public void aferUsing() {
        System.out.println(" ----- > after < ----- \n");
    }
}

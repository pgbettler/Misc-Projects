class Q1
{
    public static boolean power_of_two(int a)
    {
        //If a % 2 isn't 0, it can't be a power of 2.
        while(a % 2 == 0) {
            a = a / 2;
            if (a == 1) {
                return true;
            }
        }
        return false;
    }
}

class Rextester
{
    public static void main(String[] args)
    {
       boolean ans = Q1.power_of_two(3);
    }
}

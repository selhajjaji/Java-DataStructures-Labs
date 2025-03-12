public class Product {



    public static int getProduct(int m , int n){
       if(n==0) return n ;
        return m+getProduct( m , n-1);
    }

}

package ch19.g.test;

public class Test01 {
  int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
  
  List list = new List(a);
  
  EvenFilter filter = new EvenFilter();
  
  int[] r = list.toArray(filter);
}

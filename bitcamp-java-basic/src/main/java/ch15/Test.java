package ch15;

public class Test {
  public static void main(String[] args) {
    Integer i = new Integer(100);
    Long l = new Long(120L);
    Boolean b = new Boolean(true);
    Double d = new Double(3.14);
    Byte by = new Byte((byte) 33);
    Short s = new Short((short) 13);
    Float f = new Float(3.22f); 
    Character c = new Character('a');
    Character c2= new Character((char) 48);
    
    
    System.out.println(i.hashCode());
    System.out.println(l.hashCode());
    System.out.println(b.hashCode());
    System.out.println(d.hashCode());
    System.out.println(by.hashCode());
    System.out.println(s.hashCode());
    System.out.println(f.hashCode());
    System.out.println(c.hashCode());
    System.out.println(c2.hashCode());
    
    
    
  }
}

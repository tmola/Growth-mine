
### String

> **1. String类是final类，也即意味着String类不能被继承，并且它的成员方法都默认为final方法;**

> **2. String类其实是通过char数组来保存字符串的;**

> **3. 操作都不是在原有的字符串上进行的，而是重新生成了一个新的字符串对象,进行这些操作后，最原始的字符串并没有被改变;**

> **4. `String str = "hello world"`和`String str = new String("hello world")`的区别**

```Java
public static void main(String[] args) {
        String str1 = "hello world";
        String str2 = new String("hello world");
        String str3 = "hello world";
        String str4 = new String("hello world");

        System.out.println(str1==str2);
        System.out.println(str1==str3);
        System.out.println(str2==str4);
    }
```
运行结果：
```shell
false
true
false
```
原因分析：<center>
>`String str1 = "hello world"`和 `String str3 = "hello world"`都在编译期间生成了字面常量和符号引用;
运行期间字面常量`"hello world"`被存储在运行时常量池（当然只保存了一份）。
通过这种方式来将String对象跟引用绑定的话，JVM执行引擎会先在运行时常量池查找是否存在相同的字面常量，
如果存在，则直接将引用指向已经存在的字面常量；否则在运行时常量池开辟一个空间来存储该字面常量，并将引用指向该字面常量。

>通过new关键字来生成对象是在堆区进行的，而在堆区进行对象生成的过程是不会去检测该对象是否已经存在的。
因此通过new来创建对象，创建出的一定是不同的对象，即使字符串的内容是相同的
</center>

### StringBuffer 与 StringBuilder
>**1.与`String`类不同的是，`StringBuffer`和 `StringBuilder `类的对象能够被多次的修改，并且不产生新的未使用对象。**

>**2. `StringBuilder` 类在 Java 5 中被提出，它和 `StringBuffer` 之间的最大不同在于 `StringBuilder` 的方法不是线程安全的（不能同步访问）**

>**3. `String`、`StringBuilder`、`StringBuffer`三者的执行效率：**<br/><center>
`StringBuilder > StringBuffer > String`<br/></center>
(当然这个是相对的，不一定在所有情况下都是这样。)<br/>
+ 当字符串相加操作或者改动较少的情况下，建议使用 String str="hello"这种形式
+ 当字符串相加操作较多的情况下，建议使用StringBuilder
+ 如果采用了多线程，则使用StringBuffer

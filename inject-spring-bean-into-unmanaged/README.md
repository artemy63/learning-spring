Covered next scenario: inject Spring beans into none spring object (POJO)

There I don't consider a variant with spring bean with static method which can provide beans from injected application context

Instead, I use AspectJ. 
Note than just using `@Configurable` on unmanaged class and `@EnableSpringConfigured` on `Configuration` class it is not enough.
You should weave code, by my understanding it means that during compilation it is necessary to instrument byte code with applying Spring into plain java.
Compile-time `aspectj-maven-plugin` is used for that goal 
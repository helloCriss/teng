package cn.mlg.teng.ta;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class MyProgram {
    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     * 并被同一个System ClassLoader装载
     * 被统一的安全策略(security policy)和上下文(context)管理
     *
     * @param agentOps
     * @param inst
     * @author SHANHY
     * @create  2016年3月30日
     */
    public static void    premain(String agentOps, Instrumentation inst){
        System.out.println( Thread.currentThread());
        System.out.println("agentArgs : " + agentOps);
      //  inst.addTransformer(new DefineTransformer(), true);
    }

    /**
     * 如果不存在 premain(String agentOps, Instrumentation inst)
     * 则会执行 premain(String agentOps)
     *
     * @param agentOps
     * @author SHANHY
     * @create  2016年3月30日
     */
    public static void premain(String agentOps){

        System.out.println("====premain方法执行2====");
        System.out.println(agentOps);
    }

    static class DefineTransformer implements ClassFileTransformer {


        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("premain load Class:" + className);
            return classfileBuffer;
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Thread(){
            @Override
            public void run() {
                System.out.println("11");
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                System.out.println("22");
            }
        }.start();

        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

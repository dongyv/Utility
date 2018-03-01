import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
	public static void main(String[] args){  
        ArrayBlockingQueue<Runnable> arrayWorkQueue = new ArrayBlockingQueue(10);  
        LinkedBlockingDeque<Runnable> linkedWorkQueue = new LinkedBlockingDeque();  
        int count = 20;  
        ExecutorService threadPool = new ThreadPoolExecutor(5, //corePoolSize�̳߳��к����߳���  
                10, //maximumPoolSize �̳߳�������߳���  
                60, //�̳߳����̵߳�������ʱ�䣬�������ʱ������߳̽�������  
                TimeUnit.SECONDS,//ʱ�䵥λ  
                //�����ǲ����н���к��޽���е�����  
                arrayWorkQueue,  
                //linkedWorkQueue,  
  
                //������jdk������ִ�в���  
                //new ThreadPoolExecutor.AbortPolicy()  ���ֲ���ֱ���׳��쳣����������  
                //new ThreadPoolExecutor.DiscardPolicy() ���ֲ��Ժ�AbortPolicy����һ����Ҳ�Ƕ�������ֻ���������׳��쳣��  
                //new ThreadPoolExecutor.CallerRunsPolicy() //�̵߳������и������ execute �����˲����ṩ�򵥵ķ������ƻ��ƣ��ܹ�������������ύ�ٶȡ�û�����ף���ʱ���ҵ�main�߳�ִ�е�task5  
                new ThreadPoolExecutor.DiscardOldestPolicy()//���ִ�г�����δ�رգ���λ�ڹ�������ͷ�������񽫱�ɾ����Ȼ������ִ�г�������ٴ�ʧ�ܣ����ظ��˹��̣�  
        );  
  
        for (int i = 1; i <= count ;i++){  
            TestThreadPoolTask task = new TestThreadPoolTask(i,"name"+i);  
            threadPool.execute(task);  
        }  
  
        threadPool.shutdown();  
  
    }  
}

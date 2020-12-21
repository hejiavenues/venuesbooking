
//生产者消费者问题
 
public class ProducerConsumer {
	private int[] source=new int[5];  //共享资源
	private int count=0;   //统计个数
	private int index=0;    //数组下标
	
	//生产商品
	public synchronized void produce() {
		//如果大于容器的容量，就停止生产，等待消费者消费
		while(index>=source.length) {
			try {
				System.out.println(Thread.currentThread().getName()+"准备等待.........");
				wait();
				System.out.println(Thread.currentThread().getName()+"等待结束");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"生产第"+count+"个数据");
		source[index++]=count++;
		notifyAll();
	}
	
	//消费商品
	public synchronized void consume() {
		//如果没有商品可以消费，就等待生产者生产
		while(index<=0) {
			try {
				System.out.println(Thread.currentThread().getName()+"准备等待.........");
				wait();
				System.out.println(Thread.currentThread().getName()+"等待结束");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"消费第"+source[--index]+"个数据");
		notifyAll();
	}
	
	//生产者
	class Producer implements Runnable{
	
		@Override
		public void run() {
			for(int i=0;i<10;i++){
				produce();
			}			
		}
		
	}
	//消费者
	class Consumer implements Runnable{
		
		@Override
		public void run() {
			for(int i=0;i<10;i++){
				consume();
			}			
		}
		
	}
	
	public static void main(String[] args) {
		ProducerConsumer pc=new ProducerConsumer();
		Producer p=pc.new Producer();
		Consumer c=pc.new Consumer();
		Thread t1=new Thread(p);
		t1.setName("生产者0");
		Thread t2=new Thread(c);
		t2.setName("消费者0");
		Thread t3=new Thread(c);
		t3.setName("消费者1");
		t1.start();
		t2.start();
		t3.start();
	}
 
}
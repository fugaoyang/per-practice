package com.test.reentrantLock;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * Queue接口与List、Set同一级别，都是继承了Collection接口。
 * LinkedList实现了Queue接 口。Queue接口窄化了对LinkedList的方法的访问权限（即在方法中的参数类型如果是Queue时，就完全只能访问Queue接口所定义的方法 了，而不能直接访问 LinkedList的非Queue的方法），以使得只有恰当的方法才可以使用。
 * BlockingQueue 继承了Queue接口。

队列是一种数据结构．它有两个基本操作：在队列尾部加人一个元素，和从队列头部移除一个元素就是说，
队列以一种先进先出的方式管理数据，如果你试图向一个 已经满了的阻塞队列中添加一个元素或者是从一个空的阻塞队列中移除一个元索，将导致线程阻塞．
在多线程进行合作时，阻塞队列是很有用的工具。工作者线程可 以定期地把中间结果存到阻塞队列中而其他工作者线线程把中间结果取出并在将来修改它们。
队列会自动平衡负载。如果第一个线程集运行得比第二个慢，则第二个 线程集在等待结果时就会阻塞。如果第一个线程集运行得快，那么它将等待第二个线程集赶上来。
下表显示了jdk1.5中的阻塞队列的操作：

add        增加一个元索            如果队列已满，则抛出一个IIIegaISlabEepeplian异常
remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
offer       添加一个元素并返回true       如果队列已满，则返回false
poll         移除并返问队列头部的元素    如果队列为空，则返回null
peek       返回队列头部的元素             如果队列为空，则返回null
put         添加一个元素                      如果队列满，则阻塞
take        移除并返回队列头部的元素     如果队列为空，则阻塞

remove、element、offer 、poll、peek 其实是属于Queue接口
最后，我们有阻塞操作put和take。put方法在队列满时阻塞，take方法在队列空时阻塞;

阻塞队列（BlockingQueue）是一个支持两个附加操作的队列。这两个附加的操作是：在队列为空时，获取元素的线程会等待队列变为非空。
当队列满时，存储元素的线程会等待队列可用。阻塞队列常用于生产者和消费者的场景，生产者是往队列里添加元素的线程，消费者是从队列里拿元素的线程。
阻塞队列就是生产者存放元素的容器，而消费者也只从容器里拿元素.

JDK7提供了7个阻塞队列。分别是
ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。（FIFO）在构造时需要指定容量,并可以选择是否需要公平性;
LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。（FIFO）最大长度为Integer.MAX_VALUE
PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
DelayQueue：一个使用优先级队列实现的无界阻塞队列。
SynchronousQueue：一个不存储元素的阻塞队列。
LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。

使用阻塞队列两个显著的好处就是：多线程操作共同的队列时不需要额外的同步，另外就是队列会自动平衡负载，
即那边（生产与消费两边）处理快了就会被阻塞掉，从而减少两边的处理速度差距。
 * 
 */

public class BlockingQueueTest {
	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
//		String directory = in.nextLine();
//		System.out.print("Enter keyword (e.g. volatile): ");
//		String keyword = in.nextLine();
		String directory = "/Users/fugaoyang/copy";
		String keyword = "ee716c0f-cb36-4080-8811-b3e1f6afe296";
		final int FILE_QUEUE_SIZE = 3;// 阻塞队列大小
		final int SEARCH_THREADS = 10;// 关键字搜索线程个数

		// 基于ArrayBlockingQueue的阻塞队列
		BlockingQueue<File> queue = new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);

		// 只启动一个线程来搜索目录
		FileEnumerationTask enumerator = new FileEnumerationTask(queue, new File(directory));
		new Thread(enumerator,"put进程").start();

		// 启动100个线程用来在文件中搜索指定的关键字
		for (int i = 1; i <= SEARCH_THREADS; i++)
			new Thread(new SearchTask(queue, keyword), "search进程" + i).start();
	}
}

class FileEnumerationTask implements Runnable {
	// 哑元文件对象，放在阻塞队列最后，用来标示文件已被遍历完
	public static File DUMMY = new File("结束文件");

	private BlockingQueue<File> queue;
	private File startingDirectory;

	public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
		this.queue = queue;
		this.startingDirectory = startingDirectory;
	}

	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() +"=============put file begin");
			enumerate(startingDirectory);
			System.out.println(Thread.currentThread().getName() +"=============put file end");
			queue.put(DUMMY);// 执行到这里说明指定的目录下文件已被遍历完
		} catch (InterruptedException e) {
		}
	}

	// 将指定目录下的所有文件以File对象的形式放入阻塞队列中
	public void enumerate(File directory) throws InterruptedException {
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory())
				enumerate(file);
			else
				// 将元素放入队尾，如果队列满，则阻塞
				queue.put(file);
			    System.out.println(Thread.currentThread().getName() +"=============put file: " + file.getName());
		}
	}
}

class SearchTask implements Runnable {
	private BlockingQueue<File> queue;
	private String keyword;

	public SearchTask(BlockingQueue<File> queue, String keyword) {
		this.queue = queue;
		this.keyword = keyword;
	}

	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + "=============begin search");
			boolean done = false;
			while (!done) {
				// 取出队首元素，如果队列为空，则阻塞
				File file = queue.take();
				System.out.println(Thread.currentThread().getName() + "=============取出文件 " + file.getName());
				if (file == FileEnumerationTask.DUMMY) {
					// 取出来后重新放入，好让其他线程读到它时也很快的结束
					queue.put(file);
					done = true;
				} else
					search(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
		}
	}

	public void search(File file) throws IOException {
		Scanner in = new Scanner(new FileInputStream(file));
		int lineNumber = 0;
		while (in.hasNextLine()) {
			lineNumber++;
			String line = in.nextLine();
			if (line.contains(keyword))
				System.out.printf(Thread.currentThread().getName() + "**success***"+"%s:%d:%s%n", file.getPath(), lineNumber, line);
		}
		in.close();
	}
}

//我个人觉得，如果不放进去的话，会造成这样的情况：
//起了多个线程，但是由于queue为空了，所以肯定会有线程一直阻塞着。
//这些阻塞表现在进入while之后，一直阻塞在queue.take()，无法跳出while。
//
//所以 LZ就做了一步queue.put(file)，这样可以让这些阻塞的线程跳出while


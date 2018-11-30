package cn.et.yitao.pay.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/****
 * 上下文监听器，创建线程池放入上下文对象中，对于异步请求使用该线程池处理
 * 
 * 
 * 创建共享变量tradeService
 * @author chenwei
 *
 */
public class AppContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// create the thread pool
		ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(100));
		sce.getServletContext().setAttribute("executor", executor);
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) sce.getServletContext()
				.getAttribute("executor");
		executor.shutdown();

	}

}

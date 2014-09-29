package test.net.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.ITest;

@SuppressWarnings("unused")
public class MinaServerTest implements ITest {
	
	private static Logger log = LoggerFactory.getLogger("minaLogger");

	public static void main(String[] args) {
		new MinaServerTest().test();
	}

	@Test
	@Override
	public void test() {
		NioSocketAcceptor acceptor = new NioSocketAcceptor(4);
		acceptor.getFilterChain().addLast(
				"codex",
				new ProtocolCodecFilter(new TextLineCodecFactory(
						Constants.charset)));
		//acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.setHandler(new TimeServerHandler());
		acceptor.getSessionConfig().setReadBufferSize(1024*2);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 20);
		
		try {
			acceptor.bind(new InetSocketAddress(Constants.ServerPort));
			log.info("server start");
			log.info("listen port {}",Constants.ServerPort);
		} catch (IOException e) {
			log.warn(e.getMessage(), e);
		}
	}

}

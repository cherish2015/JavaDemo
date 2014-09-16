package test.net.mina;

import java.util.Date;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.ITest;

public class TimeServerHandler implements IoHandler, ITest {
	
	private static Logger log = LoggerFactory.getLogger("HandlerLogger");

	@Override
	public void test() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exceptionCaught(IoSession arg0, Throwable e)
			throws Exception {
		log.error(e.getMessage(), e);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		log.info("messageReceived:"+(String) message);
		session.write(new Date());
	}

	@Override
	public void messageSent(IoSession arg0, Object message) throws Exception {
		log.info("messageSent:"+ message.toString()+"\n");
	}

	@Override
	public void sessionClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionCreated(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionOpened(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}

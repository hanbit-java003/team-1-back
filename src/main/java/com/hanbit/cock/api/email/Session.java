package com.hanbit.cock.api.email;

import java.util.Properties;

import javax.mail.Authenticator;

public interface Session extends javax.websocket.Session{
	
	javax.mail.Session getInstance(Properties props, Authenticator authenticator);
}

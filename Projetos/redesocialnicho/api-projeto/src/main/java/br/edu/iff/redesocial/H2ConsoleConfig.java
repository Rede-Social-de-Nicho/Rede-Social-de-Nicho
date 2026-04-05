package br.edu.iff.redesocial;

import org.h2.server.web.JakartaWebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2ConsoleConfig { // Esta classe é responsável por configurar o console do H2, permitindo que os desenvolvedores acessem a interface web do banco de dados H2 para visualizar e manipular os dados durante o desenvolvimento.

    @Bean
    public ServletRegistrationBean<JakartaWebServlet> h2Console() { // Este método registra o servlet do console do H2, mapeando-o para a URL "/h2-console/*". Isso permite que os desenvolvedores acessem o console do H2 através dessa URL para gerenciar o banco de dados durante o desenvolvimento.
        ServletRegistrationBean<JakartaWebServlet> bean =
            new ServletRegistrationBean<>(new JakartaWebServlet(), "/h2-console/*");
        bean.setLoadOnStartup(1);
        return bean;
    }
}
    package asm2.config;

    import org.springframework.web.filter.CharacterEncodingFilter;
    import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
    import javax.servlet.Filter;
    import javax.servlet.MultipartConfigElement;
    import javax.servlet.ServletRegistration;

    public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
        @Override
        protected Class<?>[] getRootConfigClasses() {
            return null;
        }

        @Override
        protected Class<?>[] getServletConfigClasses() {
            return new Class[] { AppConfig.class };
        }

        @Override
        protected String[] getServletMappings() {
            return new String[] { "/" };
        }

            @Override
            protected Filter[] getServletFilters() {
                CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
                encodingFilter.setEncoding("UTF-8");
                encodingFilter.setForceEncoding(true);
                return new Filter[]{encodingFilter};
            }








    }

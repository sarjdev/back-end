package dev.sarj.sarjdev.service.configuration.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * {@code ShouldProvideEPDKCondition} is a condition that always returns true.
 * This condition can be used with Spring's {@link org.springframework.context.annotation.Conditional @Conditional}
 * annotation to conditionally include a bean definition based on this criteria.
 *
 * <p>
 * EPDK stands for "Enerji Piyasası Düzenleme Kurumu," which is the Energy Market Regulatory Authority in Turkey.
 * This condition can be used in scenarios where beans related to EPDK are to be conditionally provided.
 * </p>
 *
 * <pre class="code">
 * &#064;Component
 * &#064;Conditional(ShouldProvideEPDKCondition.class)
 * public class EPDKService {
 *     // EPDK related service implementation
 * }
 * </pre>
 *
 * <p>Example usage:</p>
 * <pre class="code">
 * &#064;Configuration
 * public class AppConfig {
 *
 *     &#064;Bean
 *     &#064;Conditional(ShouldProvideEPDKCondition.class)
 *     public EPDKService epdkService() {
 *         return new EPDKService();
 *     }
 * }
 * </pre>
 *
 * @see org.springframework.context.annotation.Conditional
 */
public class ShouldProvideEPDKCondition implements Condition {

    /**
     * Determines if the condition matches.
     *
     * @param context  the condition context
     * @param metadata the metadata of the {@code @Configuration} class or {@code @Bean} method being checked
     * @return always {@code true}, indicating that the condition is always met
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return true;
    }
}

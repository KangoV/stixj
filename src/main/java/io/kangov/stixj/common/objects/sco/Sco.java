package io.kangov.stixj.common.objects.sco;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.kangov.stixj.common.bundle.Bundleable;
import io.kangov.stixj.validation.ValidateIdOnly;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.util.UUID;

//@ValidateExtensions
public interface Sco
        extends
            Bundleable {

}

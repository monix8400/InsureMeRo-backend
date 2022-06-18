package licenta.InsureMeRo.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ChartValues {
    public List<Double> prices;
    public String label;
}
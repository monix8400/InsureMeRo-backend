package licenta.InsureMeRo.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ChartData {

    public List<ChartValues> chartValuesList = new ArrayList<>();
    public List<String> labels = new ArrayList<>();

}
package com.madss.activities;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.madss.R;
import com.madss.adapters.ReportAdapter;

public class ResultActivity extends AppCompatActivity {

    private ListView reportListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        reportListView = findViewById(R.id.report_list_view);
        ReportAdapter adapter = new ReportAdapter(this, getReportData());
        reportListView.setAdapter(adapter);
    }

    private List<Report> getReportData() {
        // Dummy data; replace with actual data retrieval logic
        List<Report> reports = new ArrayList<>();
        reports.add(new Report("MobSF Analysis", "Success"));
        reports.add(new Report("Frida Analysis", "Success"));
        reports.add(new Report("QARK Analysis", "Success"));
        return reports;
    }
}

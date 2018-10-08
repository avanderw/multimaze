package net.avdw.code.analyser;

import org.apache.commons.math3.stat.descriptive.StatisticalSummary;

public interface ICodeAnalyser {
    StatisticalSummary analyse();
}

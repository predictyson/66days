import React, { useRef, useEffect } from "react";
import { Chart, ChartType } from "chart.js/auto";

interface DoughnutChartProps {
  x: number;
}

const DoughnutChart: React.FC<DoughnutChartProps> = ({ x }) => {
  const canvasRef = useRef<HTMLCanvasElement>(null);

  useEffect(() => {
    let chartInstance: Chart | undefined;
    if (canvasRef.current) {
      const ctx = canvasRef.current.getContext("2d");
      if (ctx) {
        chartInstance = Chart.getChart(ctx);

        const data = {
          datasets: [
            {
              data: [x, 66 - x],
              backgroundColor: ["#6CD3C0", "#D9D9D9"],
            },
          ],
        };
        if (chartInstance) {
          chartInstance.destroy();
        }

        chartInstance = new Chart(ctx, {
          type: "doughnut" as ChartType,
          data: data,
        });
      }
    }
  }, [x]);

  return <canvas ref={canvasRef} width={120} height={120} />;
};

export default DoughnutChart;

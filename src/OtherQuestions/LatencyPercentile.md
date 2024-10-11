Latency percentiles (P90, P95, P99, etc.) are metrics used to measure the distribution of latency in a system. They help to understand how long it takes for a particular percentage of requests to be processed, providing insights into the performance and responsiveness of a system. Here's a breakdown of these terms:

### Understanding Latency Percentiles

- **P90 (90th Percentile)**: This means that 90% of the requests are completed within this time. In other words, only 10% of requests take longer than this threshold.
- **P95 (95th Percentile)**: Here, 95% of requests are completed within this time, indicating that only 5% of requests take longer than this threshold.
- **P99 (99th Percentile)**: This means that 99% of requests are completed within this time, meaning only 1% of requests take longer than this threshold.
- **P100 (100th Percentile)**: This represents the maximum latency observed. It shows the worst-case scenario.

### Why Use Latency Percentiles?

Using percentiles helps to avoid skewing the data by outliers. For instance, if a system occasionally experiences very high latency due to specific reasons (like a temporary server overload), the average latency could be misleading. Percentiles provide a clearer picture of user experience by showing how latency behaves for most of the requests.

### Example Table of Latency Percentiles

| **Percentile** | **Description**                     | **Example Latency (ms)** | **Interpretation**                             |
|----------------|-------------------------------------|--------------------------|------------------------------------------------|
| P50            | Median (50th Percentile)           | 100                      | Half of the requests are completed within 100 ms. |
| P90            | 90th Percentile                     | 200                      | 90% of requests are completed within 200 ms; 10% take longer. |
| P95            | 95th Percentile                     | 300                      | 95% of requests are completed within 300 ms; 5% take longer. |
| P99            | 99th Percentile                     | 400                      | 99% of requests are completed within 400 ms; 1% take longer. |
| P100           | Maximum Latency                     | 800                      | The worst-case scenario is an 800 ms response time. |

### Real-World Example

Consider a web application that processes user requests:

- Total requests: 1000
- Latency for requests (in ms):
    - 600 requests completed in 100 ms or less.
    - 300 requests completed between 100 ms and 200 ms.
    - 70 requests completed between 200 ms and 300 ms.
    - 20 requests completed between 300 ms and 400 ms.
    - 10 requests completed in 800 ms.

Calculating the percentiles:

- **P50 (Median)**: 50% of the requests are completed in 100 ms or less.
- **P90**: The 900th request (90% of 1000) is completed in 200 ms.
- **P95**: The 950th request is completed in 300 ms.
- **P99**: The 990th request is completed in 400 ms.
- **P100**: The maximum latency observed is 800 ms.

### Conclusion

Latency percentiles are essential for assessing and understanding system performance. By focusing on specific percentiles, you can identify performance bottlenecks, understand user experience, and make informed decisions about optimizations and infrastructure improvements.
/**
 * <p>
 * STIX defines a set of STIX Domain Objects (SDOs): Attack Pattern, Campaign, Course of Action,
 * Grouping, Identity, Indicator, Infrastructure, Intrusion Set, Location, Malware, Malware Analysis, Note,
 * Observed Data, Opinion, Report, Threat Actor, Tool, and Vulnerability. Each of these objects corresponds
 * to a concept commonly used in CTI.
 * </p>
 *
 * STIX Domain Objects are defined in section 4.
 *
 * <table>
 *     <tr>
 *         <td>Property Name</td>
 *         <td>SDO</td>
 *         <td>SRO</td>
 *         <td>SCO</td>
 *         <td>Language</td>
 *         <td>Markings</td>
 *         <td>Bundle</td>
 *     </tr>
 *     <tr><td>type</td><td>Required</td><td>Required</td><td>Required</td><td>Required</td><td>Required</td><td>Required</td></tr>
 *     <tr><td>spec_version</td><td>Required</td><td>Required</td><td>Optional</td><td>Required</td><td>Required</td><td>N/A</td></tr>
 * </table>
 *
 */
package io.kangov.stix.common.property;
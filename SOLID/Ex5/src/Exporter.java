/**
 * Abstract base class for exporting student data in different formats.
 * 
 * CONTRACT:
 * - export(ExportRequest) must always return a valid ExportResult (never throw, never return null)
 * - All content must be preserved without data loss or truncation
 * - Null fields should be handled consistently (either as null values in the format or empty strings)
 * - All subclasses must accept any reasonable input length without throwing exceptions
 * 
 * IMPLEMENTATION NOTES:
 * - Subclasses must not tighten preconditions (e.g., no input length restrictions)
 * - Subclasses must not weaken postconditions (e.g., must preserve all data)
 * - Subclasses are substitutable for the base type in all contexts
 */
public abstract class Exporter {
    public abstract ExportResult export(ExportRequest req);
}
